package it.uniroma3.siw.streamNet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.streamNet.service.CredenzialiService;
import it.uniroma3.siw.streamNet.service.StreamNetService;

@Controller
public class StreamNetController {

	@Autowired
	private StreamNetService streamNetService;
	
	@Autowired
	private CredenzialiService credenzialiService;
	
	@RequestMapping( value = "/films", method = RequestMethod.GET)
	public String getAllFilm(Model model){
		model.addAttribute("films", this.streamNetService.getAllFilm());
		return "films.html";
	}
	
	@RequestMapping( value = "/laMiaLista", method = RequestMethod.GET)
	public String getLaMiaLista(Model model){
		model.addAttribute("films", this.streamNetService.getAllFilm());
		return "laMiaLista.html";
	}
	
	@RequestMapping( value = "/admin/utentiModificaAbbonamento", method = RequestMethod.GET)
	public String getListaUtentiPerLaModificaAbbonamento(Model model){
		model.addAttribute("credenziali", this.credenzialiService.getAllCredenziali());
		return "credenzialiModificaAbbonamento.html";
	}
	
	@RequestMapping( value = "/series", method = RequestMethod.GET)
	public String getAllSerie(Model model) {
		model.addAttribute("series", this.streamNetService.getAllSerie());
		return "series.html";
	}
	
	@RequestMapping(value={"/","/index"}, method = RequestMethod.GET)
	public String getIndex() {
		return "redirect:/default";
	}
}
