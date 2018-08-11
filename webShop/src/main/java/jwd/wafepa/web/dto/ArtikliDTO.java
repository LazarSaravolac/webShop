package jwd.wafepa.web.dto;

public class ArtikliDTO {

	private Long id;
	private String naziv;
	private Double cena;
	
	private Long OsobaId;
	private String OsobaNaziv;
	
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
	public Long getOsobaId() {
		return OsobaId;
	}
	public void setOsobaId(Long osobaId) {
		OsobaId = osobaId;
	}
	public String getOsobaNaziv() {
		return OsobaNaziv;
	}
	public void setOsobaNaziv(String osobaNaziv) {
		OsobaNaziv = osobaNaziv;
	}
	
}
