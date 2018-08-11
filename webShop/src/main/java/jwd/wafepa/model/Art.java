package jwd.wafepa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Art {

	@Id
	@GeneratedValue
	private Long id;
	private String naziv;
	@Column(nullable=false)
	private Double cena;
	public Art() {
		super();
	}
	public Art(Long id, String naziv, Double cena) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.cena = cena;
	}
	public Art(String naziv, Double cena) {
		super();
		this.naziv = naziv;
		this.cena = cena;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public Double getCena() {
		return cena;
	}
	public void setCena(Double cena) {
		this.cena = cena;
	}
	
	
}
