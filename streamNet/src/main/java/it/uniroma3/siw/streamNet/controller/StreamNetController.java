package it.uniroma3.siw.streamNet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.streamNet.service.StreamNetService;

@Controller
public class StreamNetController {

	@Autowired
	private StreamNetService streamNetService;
	
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
	
	@RequestMapping( value = "/getAllSerie", method = RequestMethod.GET)
	public String getAllSerie(Model model) {
		model.addAttribute("serieTvs", this.streamNetService.getAllSerie());
		return "serieTv.html";
	}
}
