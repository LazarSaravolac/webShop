package jwd.wafepa.web.dto;

public class OsobaDTO {

	Long id;
	String ime;
	String prezime;
	String grad;
	Integer novac;
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
	
}
