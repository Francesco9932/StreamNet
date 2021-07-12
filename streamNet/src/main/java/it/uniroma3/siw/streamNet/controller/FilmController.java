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
		model.addAttribute("registi", this.streamNetService.getAllRegista());
		return "filmForm.html";
	}
	
	@RequestMapping(value = "/admin/filmDaRimuovere", method = RequestMethod.GET)
	public String mostraFilmDaRimuovere(Model model) {
		model.addAttribute("films",streamNetService.getAllFilm());
		return "filmsRimozione.html";
	} 
	
	@RequestMapping(value = "/admin/filmDaModificare", method = RequestMethod.GET)
	public String mostraFilmDaModificare(Model model) {
		model.addAttribute("films",streamNetService.getAllFilm());
		return "filmsModifica.html";
	} 
	
	@RequestMapping(value = "/admin/rimuoviFilm/{id}", method = RequestMethod.GET)
	public String rimuoviFilm(@PathVariable("id") Long id, Model model) {
		this.streamNetService.rimuoviFilmPerId(id);
		return "redirect:/films";
	}
	
	@RequestMapping(value="/admin/modificaFilm/{id}", method = RequestMethod.GET)
	public String getFilmDaModificare(@PathVariable("id") Long id,Model model) {
		Film film = this.streamNetService.getFilmPerId(id);
		Film filmModificato = new Film();
		filmModificato.setAttori(film.getAttori());
		filmModificato.setAnnoDiUscita(film.getAnnoDiUscita());
		filmModificato.setTitolo(film.getTitolo());
		filmModificato.setDescrizione(film.getDescrizione());
		filmModificato.setVideo(film.getVideo());
		filmModificato.setGenere(film.getGenere());
		filmModificato.setDurata(film.getDurata());
		filmModificato.setImmagine(film.getImmagine());
		filmModificato.setLingua(film.getLingua());
		filmModificato.setRegistaFilm(film.getRegistaFilm());
		filmModificato.setNellaMiaLista(film.isNellaMiaLista());
		this.streamNetService.rimuoviFilm(film);
		model.addAttribute("film",filmModificato);
		model.addAttribute("registi", this.streamNetService.getAllRegista());
		return "filmForm.html";
	}
	
	@RequestMapping( value = "/admin/film", method = RequestMethod.POST)
	public String newFilm(@ModelAttribute("film") Film film, 
			               Model model, BindingResult bindingResult) {
		this.filmValidator.validate(film, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.streamNetService.aggiungiFilm(film);
			return "redirect:/films";
		}
		model.addAttribute("registi", this.streamNetService.getAllRegista());
		return "filmForm.html";
	}
	
	@RequestMapping(value="/film/{id}",method = RequestMethod.GET)
	public String getFilm(@PathVariable("id") Long id,Model model) {
		model.addAttribute("film",this.streamNetService.getFilmPerId(id));
		return "film.html";
	}
	
	@RequestMapping( value = "/getRegistaFilm", method = RequestMethod.GET)
	public String getRegistaFilm(@PathVariable("id") Long id, Model model ) {
		model.addAttribute("registaFilm", this.streamNetService.getRegistaPerId(id));
		return "filmForm.html";
	}
	
	@RequestMapping(value = "/aggiungiFilmAllaMiaLista/{id}",method = RequestMethod.GET)
	public String aggiungiFilmAllaMiaLista(@PathVariable("id") Long id, Model model) {
		Film film = this.streamNetService.getFilmPerId(id);
		film.setNellaMiaLista(true);
		this.streamNetService.aggiungiFilm(film);
		return "redirect:/film/{id}";
	}
	
	@RequestMapping(value = "/rimuoviFilmDallaMiaLista/{id}",method = RequestMethod.GET)
	public String rimuoviFilmDallaMiaLista(@PathVariable("id") Long id, Model model) {
		Film film = this.streamNetService.getFilmPerId(id);
		film.setNellaMiaLista(false);
		this.streamNetService.aggiungiFilm(film);
		return "redirect:/film/{id}";
	}	
}
