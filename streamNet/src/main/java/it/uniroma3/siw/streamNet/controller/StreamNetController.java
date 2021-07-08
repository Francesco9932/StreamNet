package it.uniroma3.siw.streamNet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.streamNet.service.StreamNetService;

@Controller
public class StreamNetController {

	@Autowired
	private StreamNetService streamnetService;
	
	@RequestMapping( value = "/getAllFilm ", method = RequestMethod.GET)
	public String getAllFilm(Model model){
		model.addAttribute("film", this.streamnetService.getAllFilm());
		return "film.html";
	}
	
	@RequestMapping( value = "/getAllSerie", method = RequestMethod.GET)
	public String getAllSerie(Model model) {
		model.addAttribute("serie", this.streamnetService.getAllSerie());
		return "serieTv.html";
	}
	
	@RequestMapping( value = "/getIndex", method = RequestMethod.GET)
	public String getIndex(Model model) {
		return "index.html";
	}
	
}
