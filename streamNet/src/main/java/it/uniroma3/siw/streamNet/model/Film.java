package it.uniroma3.siw.streamNet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Film {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column
	private String titolo;

	@Column
	private String genere;

	@Column
	private String descrizione;

	@Column
	private String attori;

	@Column
	private int annoDiUscita;

	@Column
	private String lingua;
	
	@Column
	private boolean nellaMiaLista;
	
	@Column
	private String video;

	@ManyToOne()
	private Regista registaFilm;
	
	public Film() {
		nellaMiaLista = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getAnnoDiUscita() {
		return annoDiUscita;
	}

	public void setAnnoDiUscita(int annoDiUscita) {
		this.annoDiUscita = annoDiUscita;
	}

	public String getLingua() {
		return lingua;
	}

	public void setLingua(String lingua) {
		this.lingua = lingua;
	}

	public Regista getRegistaFilm() {
		return registaFilm;
	}

	public void setRegistaFilm(Regista registaFilm) {
		this.registaFilm = registaFilm;
	}

	public String getAttori() {
		return attori;
	}

	public void setAttori(String attori) {
		this.attori = attori;
	}

	public boolean isNellaMiaLista() {
		return nellaMiaLista;
	}

	public void setNellaMiaLista(boolean nellaMiaLista) {
		this.nellaMiaLista = nellaMiaLista;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}
}
