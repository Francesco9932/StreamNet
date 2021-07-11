package it.uniroma3.siw.streamNet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.streamNet.controller.validator.RegistaValidator;
import it.uniroma3.siw.streamNet.model.Film;
import it.uniroma3.siw.streamNet.model.Regista;
import it.uniroma3.siw.streamNet.service.StreamNetService;

@Controller
public class RegistaController {
	@Autowired
	private StreamNetService streamNetService;

	@Autowired
	private RegistaValidator registaValidator;

	@RequestMapping(value = "/admin/aggiungiRegista", method = RequestMethod.GET)
	public String aggiungiRegista(Model model) {
		model.addAttribute("regista", new Regista());
		return "registaForm.html";
	}
	
	@RequestMapping(value = "/admin/registaDaRimuovere", method = RequestMethod.GET)
	public String mostraRegistaDaRimuovere(Model model) {
		model.addAttribute("registi",streamNetService.getAllRegista());
		return "registaRimozione.html";
	}
	
	@RequestMapping(value = "/admin/rimuoviRegista/{id}", method = RequestMethod.GET)
	public String rimuoviRegista(@PathVariable("id") Long id, Model model) {
		this.streamNetService.rimuoviRegistaPerId(id);
		return "redirect:/films";
	}
	
	@RequestMapping(value = "/admin/registaDaModificare", method = RequestMethod.GET)
	public String mostraRegistaDaModificare(Model model) {
		model.addAttribute("registi",streamNetService.getAllRegista());
		return "registaModifica.html";
	}
	
	@RequestMapping(value="/admin/modificaRegista/{id}", method = RequestMethod.GET)
	public String getRegistaDaModificare(@PathVariable("id") Long id,Model model) {
		Regista regista = this.streamNetService.getRegistaPerId(id);
		Regista registaModificato = new Regista();
		registaModificato.setNome(regista.getNome());
		registaModificato.setCognome(regista.getCognome());
		registaModificato.setDataDiNascita(regista.getDataDiNascita());
		registaModificato.setBiografia(regista.getBiografia());
		this.streamNetService.rimuoviRegista(regista);
		model.addAttribute("regista",registaModificato);
		return "registaForm.html";
	}
	
	@RequestMapping(value =  "/admin/regista", method = RequestMethod.POST)
	public String newRegista(@ModelAttribute Regista regista,
			Model model, BindingResult bindingResult) {
		this.registaValidator.validate(regista, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.streamNetService.aggiungiRegista(regista);
			return "redirect:/films";
		}
		else
			return "registaForm.html";
	}


	@RequestMapping(value = "/getFilmRegista", method = RequestMethod.GET)
	public String getFilmRegista(@ModelAttribute Regista regista,
			Model model) {
		model.addAttribute("filmRegista", this.streamNetService.getFilmRegista(regista));
		return "registaForm.html";
	}

	@RequestMapping(value = "/getSerieRegista", method = RequestMethod.POST)
	public String getSerieRegista(@ModelAttribute Regista regista, Model model) {
		model.addAttribute("serieRegista", this.streamNetService.getSerieRegista(regista));
		return "registaForm.html";
	}
}
