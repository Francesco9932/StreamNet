package it.uniroma3.siw.streamNet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.streamNet.controller.validator.StagioneValidator;
import it.uniroma3.siw.streamNet.model.Film;
import it.uniroma3.siw.streamNet.model.Stagione;
import it.uniroma3.siw.streamNet.service.StreamNetService;

@Controller
public class StagioneController {

	@Autowired
	private StreamNetService streamNetService;

	@Autowired
	private StagioneValidator stagioneValidator;

	@RequestMapping(value = "/admin/aggiungiStagione", method = RequestMethod.GET)
	public String aggiungiStagione(Model model) {
		model.addAttribute("stagione", new Stagione());
		return "stagioneForm.html";
	}
	
	@RequestMapping(value = "/admin/stagioneDaRimuovere", method = RequestMethod.GET)
	public String mostraStagioneDaRimuovere(Model model) {
		model.addAttribute("stagioni",streamNetService.getAllStagione());
		return "stagioniRimozione.html";
	}
	
	@RequestMapping(value = "/admin/rimuoviStagione/{id}", method = RequestMethod.GET)
	public String rimuoviStagione(@PathVariable("id") Long id, Model model) {
		this.streamNetService.rimuoviStagionePerId(id);
		return "redirect:/stagioni";
	}
	
	@RequestMapping(value = "/admin/stagioneDaModificare", method = RequestMethod.GET)
	public String mostraStagioneDaModificare(Model model) {
		model.addAttribute("stagioni",streamNetService.getAllStagione());
		return "stagioniModifica.html";
	}
	
	@RequestMapping(value="/admin/modificaStagione/{id}", method = RequestMethod.GET)
	public String getStagioneDaModificare(@PathVariable("id") Long id,Model model) {
		Stagione stagione = this.streamNetService.getStagionePerId(id);
		Stagione stagioneModificato = new Stagione();
		stagioneModificato.setDescrizione(stagione.getDescrizione());
		this.streamNetService.rimuoviStagione(stagione);
		model.addAttribute("stagione",stagioneModificato);
		return "stagioneForm.html";
	}
	
	@RequestMapping(value =  "/admin/stagione", method = RequestMethod.POST)
	public String newStagione(@ModelAttribute Stagione stagione,
			Model model, BindingResult bindingResult) {
		this.stagioneValidator.validate(stagione, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.streamNetService.aggiungiStagione(stagione);
			return "redirect:/stagioni";
		}
		else
			return "stagioneForm.html";
	}

}
