package it.uniroma3.siw.streamNet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.streamNet.controller.validator.SerieTvValidator;
import it.uniroma3.siw.streamNet.model.SerieTv;
import it.uniroma3.siw.streamNet.service.StreamNetService;

@Controller
public class SerieTvController {
	
	@Autowired
	private StreamNetService streamNetService;
	
	@Autowired 
	private SerieTvValidator serieValidator;
	
	@RequestMapping ( value = "/admin/aggiungiSerie", method = RequestMethod.GET)
	public String aggiungiSerie(Model model) {
		model.addAttribute("serie", new SerieTv());
		return "serieForm.html";
	}
	
	@RequestMapping(value = "/admin/serieDaRimuovere", method = RequestMethod.GET)
	public String mostraSerieDaRimuovere(Model model) {
		model.addAttribute("series",streamNetService.getAllSerie());
		return "seriesRimozione.html";
	} 
	
	@RequestMapping(value = "/admin/serieDaModificare", method = RequestMethod.GET)
	public String mostraSerieDaModificare(Model model) {
		model.addAttribute("series",streamNetService.getAllSerie());
		return "seriesModifica.html";
	} 
	
	@RequestMapping(value = "/admin/rimuoviSerie/{id}", method = RequestMethod.GET)
	public String rimuoviSerie(@PathVariable("id") Long id, Model model) {
		this.streamNetService.rimuoviSeriePerId(id);
		return "series.html";
	}
	
	@RequestMapping(value="/admin/modificaSerie/{id}", method = RequestMethod.GET)
	public String getSerieDaModificare(@PathVariable("id") Long id,Model model) {
		SerieTv serie = this.streamNetService.getSeriePerId(id);
		SerieTv serieModificato = new SerieTv();
		serieModificato.setAttori(serie.getAttori());
		serieModificato.setAnnoDiUscita(serie.getAnnoDiUscita());
		serieModificato.setTitolo(serie.getTitolo());
		serieModificato.setDescrizione(serie.getDescrizione());
		this.streamNetService.rimuoviSerie(serie);
		model.addAttribute("serie",serieModificato);
		return "serieForm.html";
	}
	
	@RequestMapping( value = "/admin/serie", method = RequestMethod.POST)
	public String newSerie(@ModelAttribute("serie") SerieTv serie, 
			               Model model, BindingResult bindingResult) {
		this.serieValidator.validate(serie, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.streamNetService.aggiungiSerie(serie);
			return "redirect:/default";
		}
		return "serieForm.html";
	}
	
	@RequestMapping(value="/serie/{id}",method = RequestMethod.GET)
	public String getSerie(@PathVariable("id") Long id,Model model) {
		model.addAttribute("serie",this.streamNetService.getSeriePerId(id));
		return "serie.html";
	}
	
	@RequestMapping( value = "/getRegistaSerie", method = RequestMethod.GET)
	public String getRegistaSerie(@PathVariable("id") Long id, Model model ) {
		model.addAttribute("registaSerie", this.streamNetService.getRegistaPerId(id));
		return "serieForm.html";
	}
	
	@RequestMapping(value = "/aggiungiSerieAllaMiaLista/{id}",method = RequestMethod.GET)
	public String aggiungiSerieAllaMiaLista(@PathVariable("id") Long id, Model model) {
		SerieTv serie = this.streamNetService.getSeriePerId(id);
		serie.setNellaMiaLista(true);
		this.streamNetService.aggiungiSerie(serie);
		model.addAttribute("series",this.streamNetService.getAllSerie());
		return "laMiaLista.html";
	}
	
	@RequestMapping(value = "/rimuoviSerieDallaMiaLista/{id}",method = RequestMethod.GET)
	public String rimuoviSerieDallaMiaLista(@PathVariable("id") Long id, Model model) {
		SerieTv serie = this.streamNetService.getSeriePerId(id);
		serie.setNellaMiaLista(false);
		this.streamNetService.aggiungiSerie(serie);
		model.addAttribute("series",this.streamNetService.getAllSerie());
		return "laMiaLista.html";
	}
}
