package it.uniroma3.siw.streamNet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.streamNet.model.Regista;
import it.uniroma3.siw.streamNet.service.StreamNetService;
import it.uniroma3.siw.streamNet.validator.RegistaValidator;

@Controller
public class RegistaController {
	
	 @Autowired
	 private StreamNetService streamNetService;
	 
	 @Autowired
	 private RegistaValidator registaValidator;
	 
	 
	 @Transactional
	 @RequestMapping(value = "/aggiungiRegista", method = RequestMethod.GET)
	 public String aggiungiRegista(Model model) {
		 
		 model.addAttribute("regista", new Regista());
		 return "registaForm.html";
		 }
	 
	 @Transactional
	 @RequestMapping(value =  "/newRegista", method = RequestMethod.POST)
	 public String newRegista(@ModelAttribute Regista regista,
			                   Model model, BindingResult bindingResult) {
		 this.registaValidator.validate(regista, bindingResult);
		 if(!bindingResult.hasErrors()) {
			 this.streamNetService.aggiungiRegista(regista);
			 return "index.html";
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
