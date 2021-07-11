 package it.uniroma3.siw.streamNet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.streamNet.controller.validator.CredenzialiValidator;
import it.uniroma3.siw.streamNet.controller.validator.UtenteValidator;
import it.uniroma3.siw.streamNet.model.Credenziali;
import it.uniroma3.siw.streamNet.model.Utente;
import it.uniroma3.siw.streamNet.service.CredenzialiService;
import it.uniroma3.siw.streamNet.service.StreamNetService;

@Controller
public class AuthenticationController {
	@Autowired
	private StreamNetService streamNetService;
	
	@Autowired
	private CredenzialiService credenzialiService;
	
	@Autowired
	private UtenteValidator utenteValidator;
	
	@Autowired
	private CredenzialiValidator credenzialiValidator;
	
	@RequestMapping(value = "/register", method =RequestMethod.GET)
	public String mostraRegistrazioneForm(Model model) {
		model.addAttribute("utente", new Utente());
		model.addAttribute("credenziali", new Credenziali());
		return "registrazioneForm.html";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String mostraLoginForm(Model model) {
		return "loginForm.html";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
		return "redirect:/default";
	}
	
	@RequestMapping(value = "/default", method = RequestMethod.GET)
    public String defaultAfterLogin(Model model) {
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credenziali credenziali = credenzialiService.getCredenziali(userDetails.getUsername());
    	if (credenziali.getRuolo().equals(Credenziali.RUOLO_ADMIN)) {
    		model.addAttribute("films", this.streamNetService.getAllFilm());
    		model.addAttribute("series", this.streamNetService.getAllSerie());
            return "admin/indexAdmin.html";
        }
    	model.addAttribute("series", this.streamNetService.getAllSerie());
    	model.addAttribute("films", this.streamNetService.getAllFilm());
        return "index.html";
    }
	
	@RequestMapping(value ="/register", method = RequestMethod.POST)
	public String registrazioneUser(@ModelAttribute("utente") Utente utente, BindingResult utenteBindingResult, 
			@ModelAttribute("credenziali") Credenziali credenziali, BindingResult credenzialiBindingResult, Model model) {
		
		this.utenteValidator.validate(utente, utenteBindingResult);
		this.credenzialiValidator.validate(credenziali, credenzialiBindingResult);

		if(!utenteBindingResult.hasErrors() && !credenzialiBindingResult.hasErrors()) {
			credenziali.setUtente(utente);
			credenzialiService.salvaCredenziali(credenziali);
			return "redirect:/login";
		}
		return "registrazioneForm.html";
	}
}
