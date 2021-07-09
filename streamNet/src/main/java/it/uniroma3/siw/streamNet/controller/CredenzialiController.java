package it.uniroma3.siw.streamNet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.streamNet.controller.validator.CredenzialiValidator;
import it.uniroma3.siw.streamNet.model.Credenziali;
import it.uniroma3.siw.streamNet.service.CredenzialiService;

@Controller
public class CredenzialiController {
	@Autowired 
	private CredenzialiService credenzialiService;
	
	@Autowired 
	private CredenzialiValidator credenzialiValidator;
	
	@RequestMapping(value="/admin/modificaAbbonamentoUtente/{id}",method=RequestMethod.GET)
	public String getAbbonamentoDaModificare(@PathVariable("id")Long id, Model model) {
		Credenziali vecchieCredenziali = this.credenzialiService.getCredenziali(id);
		Credenziali nuoveCredenziali = new Credenziali();
		nuoveCredenziali.setId(vecchieCredenziali.getId());
		nuoveCredenziali.setAbbonamento(vecchieCredenziali.getAbbonamento());
		nuoveCredenziali.setPassword(vecchieCredenziali.getPassword());
		nuoveCredenziali.setRuolo(vecchieCredenziali.getRuolo());
		nuoveCredenziali.setUsername(vecchieCredenziali.getUsername());
		nuoveCredenziali.setUtente(vecchieCredenziali.getUtente());
		model.addAttribute("vecchieCredenziali",vecchieCredenziali);
		model.addAttribute("nuoveCredenziali",nuoveCredenziali);
		return "modificaAbbonamentoForm.html";
	}
	
	@RequestMapping(value="/admin/modificaAbbonamentoUtente",method=RequestMethod.POST)
	public String modificaAbbonamentoDiUnUtente(@ModelAttribute("vecchieCredenziali") Credenziali vecchieCredenziali, 
			@ModelAttribute("nuoveCredenziali") Credenziali nuoveCredenziali,
			BindingResult bindingResult, Model model) {
		this.credenzialiService.rimuoviCredenziali(vecchieCredenziali);
		this.credenzialiValidator.validate(nuoveCredenziali, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.credenzialiService.salvaCredenziali(nuoveCredenziali);
			return "redirect:/admin/utentiModificaAbbonamento";
		}
		return "modificaAbbonamentoForm.html";
	}
}
