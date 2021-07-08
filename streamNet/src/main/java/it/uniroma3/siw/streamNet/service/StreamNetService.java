package it.uniroma3.siw.streamNet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.streamNet.model.Film;
import it.uniroma3.siw.streamNet.model.Regista;
import it.uniroma3.siw.streamNet.model.SerieTv;
import it.uniroma3.siw.streamNet.repository.FilmRepository;
import it.uniroma3.siw.streamNet.repository.RegistaRepository;
import it.uniroma3.siw.streamNet.repository.SerieRepository;

@Service
public class StreamNetService {
	
	@Autowired
	private FilmRepository filmRepository;
	
	@Autowired
	private SerieRepository serieRepository;
	
	@Autowired
	private RegistaRepository registaRepository;
	
	@Transactional
	public List<Film> getAllFilm(){
		return (List<Film>) filmRepository.findAll();
	}
	
	@Transactional
	public List<SerieTv> getAllSerie(){
		return (List<SerieTv>) serieRepository.findAll();
	}
	
	@Transactional
	public boolean filmAlreadyExist(Film film) {
		List<Film> listaFilm  =this.filmRepository.findByTitolo(film.getTitolo());
		if(listaFilm.size() > 0)
			return true;
		else
			return false;
	}
	
	@Transactional
	public boolean serieAlreadyExist(SerieTv serie) {
		List<SerieTv> listaSerie = this.serieRepository.findByTitolo(serie.getTitolo());
		if(listaSerie.size() > 0 )
			return true;
		else
			return false;
	}
	
	@Transactional
	public boolean registaAlreadyExist(Regista regista) {
	List<Regista> listaRegista = this.registaRepository.findByNome(regista.getNome());
	if(listaRegista.size() > 0)
		return true;
	else
		return false;
	}
	
	@Transactional
	public Film aggiungiFilm(Film film) {
		return this.filmRepository.save(film);
	}
	
	@Transactional
	public SerieTv aggiungiSerie(SerieTv serie) {
		return this.serieRepository.save(serie);
	}
	
	@Transactional
	public Regista aggiungiRegista(Regista regista) {
		return this.registaRepository.save(regista);
	}
	
	@Transactional
	public Regista getRegistaPerId(Long id) {
		Optional<Regista>  optional = this.registaRepository.findById(id);
		if(optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public List<Film> getFilmRegista(Regista regista) {
		return this.filmRepository.findByRegistaFilm(regista);
	}

	@Transactional
	public List<SerieTv> getSerieRegista(Regista regista) {
		return this.serieRepository.findByRegistaSerie(regista);
	}
}
	

