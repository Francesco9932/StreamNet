package it.uniroma3.siw.streamNet.repository;

import it.uniroma3.siw.streamNet.model.Film;
import it.uniroma3.siw.streamNet.model.Regista;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface FilmRepository extends CrudRepository<Film, Long>{
	
	public List<Film> findByTitolo(String titolo);

	public List<Film> findByRegista(Regista regista);
}
