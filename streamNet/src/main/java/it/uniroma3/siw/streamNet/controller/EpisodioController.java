package it.uniroma3.siw.streamNet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.streamNet.controller.validator.EpisodioValidator;
import it.uniroma3.siw.streamNet.model.Episodio;
import it.uniroma3.siw.streamNet.model.Stagione;
import it.uniroma3.siw.streamNet.service.StreamNetService;

@Controller
public class EpisodioController {
	
	@Autowired
	private StreamNetService streamNetService;
	
	@Autowired 
	private EpisodioValidator episodioValidator;
	
	@RequestMapping ( value = "/admin/aggiungiEpisodio", method = RequestMethod.GET)
	public String aggiungiEpisodio(Model model) {
		model.addAttribute("episodio", new Episodio());
		model.addAttribute("stagioni", this.streamNetService.getAllStagione());
		return "episodioForm.html";
	}
	
	@RequestMapping(value = "/admin/episodioDaRimuovere", method = RequestMethod.GET)
	public String mostraEpisodioDaRimuovere(Model model) {
		model.addAttribute("episodi",streamNetService.getAllEpisodio());
		return "episodioRimozione.html";
	} 
	
	@RequestMapping(value = "/admin/episodioDaModificare", method = RequestMethod.GET)
	public String mostraEpisodioDaModificare(Model model) {
		model.addAttribute("episodi",streamNetService.getAllEpisodio());
		return "episodioModifica.html";
	} 
	
	@RequestMapping(value = "/admin/rimuoviEpisodio/{id}", method = RequestMethod.GET)
	public String rimuoviEpisodio(@PathVariable("id") Long id, Model model) {
		this.streamNetService.rimuoviEpisodioPerId(id);
		return "redirect:/default";
	}
	
	@RequestMapping(value="/admin/modificaEpisodio/{id}", method = RequestMethod.GET)
	public String getEpisodioDaModificare(@PathVariable("id") Long id,Model model) {
		Episodio episodio = this.streamNetService.getEpisodioPerId(id);
		Episodio episodioModificato = new Episodio();
		episodioModificato.setNumero(episodio.getNumero());
		episodioModificato.setDescrizione(episodio.getDescrizione());
		episodioModificato.setDurata(episodio.getDurata());
		this.streamNetService.rimuoviEpisodio(episodio);
		model.addAttribute("episodio",episodioModificato);
		model.addAttribute("stagioni", this.streamNetService.getAllStagione());
		return "episodioForm.html";
	}
	
	@RequestMapping( value = "/admin/episodio", method = RequestMethod.POST)
	public String newEpisodio(@ModelAttribute("episodio") Episodio episodio, 
			               Model model, BindingResult bindingResult) {
		this.episodioValidator.validate(episodio, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.streamNetService.aggiungiEpisodio(episodio);
			return "redirect:/default";
		}
		model.addAttribute("stagioni", this.streamNetService.getAllStagione());
		return "episodioForm.html";
	}
	
	@RequestMapping(value="/episodio/{id}",method = RequestMethod.GET)
	public String getEpisodio(@PathVariable("id") Long id,Model model) {
		model.addAttribute("episodio",this.streamNetService.getEpisodioPerId(id));
		return "episodio.html";
	}
	
	@RequestMapping(value = "/admin/mostraEpisodiDaAggiungereAllaStagione", method = RequestMethod.GET)
	public String mostraEpisodiDaAggiungereAllaStagione(Model model) {
		model.addAttribute("episodi",streamNetService.getAllEpisodio());
		return "aggiungiEpisodioAllaStagione.html";
	}
	
	@RequestMapping(value = "/admin/aggiungiEpisodioAllaStagione/{idEpisodio}/{id}", method = RequestMethod.GET)
	public String aggiungiStagioneAllaSerie(@PathVariable("id") Long id, 
			@PathVariable("idEpisodio")Long idEpisodio, Model model) {
		Episodio episodioDaAggiungere = this.streamNetService.getEpisodioPerId(idEpisodio);
		Stagione stagione = this.streamNetService.getStagionePerId(id);
		List<Episodio> episodiDellaSerie = stagione.getEpisodi();
		episodiDellaSerie.add(episodioDaAggiungere);
		stagione.setEpisodi(episodiDellaSerie);
		streamNetService.aggiungiStagione(stagione);
		return "redirect:/default";
	}
}
