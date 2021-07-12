package it.uniroma3.siw.streamNet.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.streamNet.model.Episodio;

public interface EpisodioRepository extends CrudRepository<Episodio, Long> {

	public List<Episodio> findByNumeroAndId(Long numero, Object episodiogetId);
}
