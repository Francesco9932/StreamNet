package it.uniroma3.siw.streamNet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.streamNet.model.Credenziali;
import it.uniroma3.siw.streamNet.repository.CredenzialiRepository;
@Service
public class CredenzialiService {
	
	@Autowired
	private CredenzialiRepository credenzialiRepository;
	
	@Autowired
	protected PasswordEncoder passwordEncoder;
	
	@Transactional
	public Credenziali getCredenziali(String username) {
		Optional<Credenziali> optional = this.credenzialiRepository.findByUsername(username);
		return optional.orElse(null);
	}
	
	@Transactional
	public Credenziali getCredenziali(Long id) {
		Optional<Credenziali> optional = this.credenzialiRepository.findById(id);
		return optional.orElse(null);
	}
	
	@Transactional
	public void rimuoviCredenziali(Credenziali credenziali) {
		this.credenzialiRepository.delete(credenziali);
	}
	
	@Transactional
	public List<Credenziali> getAllCredenziali() {
		return (List<Credenziali>) this.credenzialiRepository.findAll();
	}
	
	@Transactional
	public Credenziali salvaCredenziali(Credenziali credenziali) {
		credenziali.setRuolo(Credenziali.RUOLO_UTENTE);
		credenziali.setPassword(this.passwordEncoder.encode(credenziali.getPassword()));
		return this.credenzialiRepository.save(credenziali);
	}
}
