package it.uniroma3.siw.streamNet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import it.uniroma3.siw.streamNet.model.Utente;
import it.uniroma3.siw.streamNet.repository.UtenteRepository;


@Service
public class UtenteService {
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Transactional
	public Utente getUtente(Long id) {
		Optional<Utente> optional =  this.utenteRepository.findById(id);
		return optional.orElse(null);
		}
	
	@Transactional
	public Utente salvaUtente(Utente utente) {
		return this.utenteRepository.save(utente);
	}
	
	@Transactional
	public List<Utente> getUsers(){
		List<Utente> result = new ArrayList<>();
		Iterable<Utente> iterable = this.utenteRepository.findAll();
		for(Utente u : iterable)
			result.add(u);
		return result;
	}

}
