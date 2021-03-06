package it.uniroma3.siw.streamNet.repository;

import it.uniroma3.siw.streamNet.model.SerieTv;
import it.uniroma3.siw.streamNet.model.Regista;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SerieRepository extends CrudRepository<SerieTv, Long> {
	
	public List<SerieTv> findByTitolo(String titolo);

	public List<SerieTv> findByRegistaSerie(Regista regista);
}
