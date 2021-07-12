package it.uniroma3.siw.streamNet.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Stagione {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	private String descrizione;
	
	@Column
	private Long numero;

	@ManyToOne
	private SerieTv serieTv;
	
	@OneToMany(mappedBy = "stagione", cascade = CascadeType.ALL)
	private List<Episodio> episodi;
	
	public Stagione() {
		this.episodi = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public SerieTv getSerieTv() {
		return serieTv;
	}

	public void setSerieTv(SerieTv serieTv) {
		this.serieTv = serieTv;
	}

	public List<Episodio> getEpisodi() {
		return episodi;
	}

	public void setEpisodi(List<Episodio> episodi) {
		this.episodi = episodi;
	}
	
	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}
}
