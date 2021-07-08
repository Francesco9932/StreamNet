package it.uniroma3.siw.streamNet.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.streamNet.model.Regista;

public interface RegistaRepository extends CrudRepository<Regista, Long> {
	
	public List<Regista> findByNome(String nome);
}
