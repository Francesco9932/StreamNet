package it.uniroma3.siw.streamNet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.streamNet.model.SerieTv;
import it.uniroma3.siw.streamNet.repository.SerieRepository;
import it.uniroma3.siw.streamNet.service.StreamNetService;
import it.uniroma3.siw.streamNet.validator.SerieTvValidator;

@Controller
public class SerieTvController {
	
		@Autowired
		private StreamNetService streamNetService;

		@Autowired
		private SerieRepository serieRepository;
		
		@Autowired
		private SerieTvValidator serieValidator;
		
		
		@Transactional
		@RequestMapping( value = "/aggiungiSerie", method = RequestMethod.GET)
		public String aggiungiSerie(Model model) {
			model.addAttribute("serieTv", new SerieTv());
			return "serieTvForm.html";
			}
		
		@Transactional
		@RequestMapping( value = "/newSerieTv", method = RequestMethod.POST)
		public String newSerieTv(@ModelAttribute SerieTv serieTv,
				                  Model model, BindingResult bindingResult) {
			this.serieValidator.validate(serieTv, bindingResult);
			if(!bindingResult.hasErrors()) {
				this.streamNetService.aggiungiSerie(serieTv);
				return "index.html";
			}
			else
				return "serieTvForm.html";
			}
		
		@Transactional
		@RequestMapping(value = "/getRegistaSerie", method = RequestMethod.GET)
		public String getRegistaSerie(@PathVariable("id") Long id, Model model) {
			model.addAttribute("registaSerie", this.streamNetService.getRegistaPerId(id));
			return "serieTvForm.html";
			
		}
}
