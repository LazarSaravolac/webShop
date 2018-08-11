package jwd.wafepa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class Osoba {
	
	@Id
	@GeneratedValue
	Long id;
	String ime;
	String prezime;
	String grad;
	@Column(nullable=false)
	Integer novac;
	
	@OneToMany(mappedBy="osoba",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	List<Artikli>artikli=new ArrayList<>();

	public Osoba() {
		super();
	}

	public Osoba(String ime, String prezime, String grad, Integer novac, List<Artikli> artikli) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.grad = grad;
		this.novac = novac;
		this.artikli = artikli;
	}

	public Osoba(Long id, String ime, String prezime, String grad, Integer novac, List<Artikli> artikli) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.grad = grad;
		this.novac = novac;
		this.artikli = artikli;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public Integer getNovac() {
		return novac;
	}

	public void setNovac(Integer novac) {
		this.novac = novac;
	}

	public List<Artikli> getArtikli() {
		return artikli;
	}

	public void setArtikli(List<Artikli> artikli) {
		this.artikli = artikli;
	}
	
	public void addUArtikli(Artikli artikli){
		this.artikli.add(artikli);
		
		if(!this.equals(artikli.getOsoba())){
			artikli.setOsoba(this);
		}
	}
}
