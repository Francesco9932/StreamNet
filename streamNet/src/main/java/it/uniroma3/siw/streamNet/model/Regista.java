package it.uniroma3.siw.streamNet.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Regista {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private String cognome;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column
	private LocalDate dataDiNascita;
	
	@Column
	private String biografia;
	
	@OneToMany(mappedBy = "registaFilm")
	private List<Film> film;
	
	@OneToMany(mappedBy = "registaSerie")
	private List<SerieTv> serieTv;
	
	public Regista() {
		this.film = new ArrayList<>();
		this.serieTv = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public List<Film> getFilm() {
		return film;
	}

	public void setFilm(List<Film> film) {
		this.film = film;
	}

	public List<SerieTv> getSerieTv() {
		return serieTv;
	}

	public void setSerieTv(List<SerieTv> serieTv) {
		this.serieTv = serieTv;
	}
}
