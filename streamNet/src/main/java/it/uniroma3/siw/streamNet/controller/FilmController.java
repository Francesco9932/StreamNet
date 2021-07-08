package it.uniroma3.siw.streamNet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.streamNet.controller.validator.FilmValidator;
import it.uniroma3.siw.streamNet.model.Film;
import it.uniroma3.siw.streamNet.service.StreamNetService;

@Controller
public class FilmController {
	
	@Autowired
	private StreamNetService streamNetService;
	
	@Autowired 
	private FilmValidator filmValidator;
	
	@RequestMapping ( value = "/admin/aggiungiFilm", method = RequestMethod.GET)
	public String aggiungiFilm(Model model) {
		model.addAttribute("film", new Film());
		return "filmForm.html";
	}
	
	@RequestMapping( value = "/admin/newFilm", method = RequestMethod.POST)
	public String newFilm(@ModelAttribute("film") Film film, 
			               Model model, BindingResult bindingResult) {
		this.filmValidator.validate(film, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.streamNetService.aggiungiFilm(film);
			return "redirect:/films";
		}
		return "filmForm.html";
	}
	
	@RequestMapping( value = "/getRegistaFilm", method = RequestMethod.GET)
	public String getRegistaFilm(@PathVariable("id") Long id, Model model ) {
		model.addAttribute("registaFilm", this.streamNetService.getRegistaPerId(id));
		return "filmForm.html";
	}
}
