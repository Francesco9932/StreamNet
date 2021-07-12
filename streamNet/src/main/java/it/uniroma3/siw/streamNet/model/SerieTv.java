package it.uniroma3.siw.streamNet.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class SerieTv {

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
	private String immagine;

	@ManyToOne()
	private Regista registaSerie;

	@OneToMany(mappedBy = "serieTv")
	private List<Stagione> stagioni;

	public SerieTv() {
		this.stagioni = new ArrayList<>();
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

	public Regista getRegistaSerie() {
		return registaSerie;
	}

	public void setRegistaSerie(Regista registaSerie) {
		this.registaSerie = registaSerie;
	}

	public List<Stagione> getStagioni() {
		return stagioni;
	}

	public void setStagioni(List<Stagione> stagioni) {
		this.stagioni = stagioni;
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

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}
}
