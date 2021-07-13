package it.uniroma3.siw.streamNet.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.streamNet.model.Episodio;
import it.uniroma3.siw.streamNet.model.Stagione;

public interface EpisodioRepository extends CrudRepository<Episodio, Long> {
	public List<Episodio> findByNumero(Long numero);
	
	public List<Episodio> findByNumeroAndStagione(Long numero, Stagione stagione);
}
