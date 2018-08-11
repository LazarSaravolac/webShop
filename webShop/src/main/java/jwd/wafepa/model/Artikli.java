package jwd.wafepa.model;

import javax.persistence.*;

@Entity
@Table
public class Artikli {
	@Id
	@GeneratedValue
	Long id;
	String naziv;
	@Column(nullable=false)
	Double cena;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	Osoba osoba;

	public Artikli() {
		super();
	}

	public Artikli(Long id, String naziv, Double cena, Osoba osoba) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.cena = cena;
		this.osoba = osoba;
	}

	public Artikli(String naziv, Double cena, Osoba osoba) {
		super();
		this.naziv = naziv;
		this.cena = cena;
		this.osoba = osoba;
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

	public Osoba getOsoba() {
		return osoba;
	}

	public void setOsoba(Osoba osoba) {
		this.osoba = osoba;
	}
	
	
	
}
