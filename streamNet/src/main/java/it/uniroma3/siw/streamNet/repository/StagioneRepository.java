package it.uniroma3.siw.streamNet.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.streamNet.model.Stagione;

public interface StagioneRepository extends CrudRepository<Stagione, Long> {

	public List<Stagione> findByNumero(Long numero);
}
