package jwd.wafepa;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Art;
import jwd.wafepa.model.Artikli;
import jwd.wafepa.model.Osoba;
import jwd.wafepa.service.ArtService;
import jwd.wafepa.service.ArtikliService;
import jwd.wafepa.service.OsobaService;


@Component
public class TestData {

	@Autowired
	private OsobaService osobaService;
	
	@Autowired
	private ArtikliService artikliService;
	
	@Autowired
	private ArtService artService;
	
	@PostConstruct
	public void init() {
		Osoba o1=new Osoba();
		o1.setGrad("Novi Sad");
		o1.setIme("Lazar");
		o1.setNovac(500);
		o1.setPrezime("Saravolac");
		osobaService.save(o1);

		Osoba o2=new Osoba();
		o2.setGrad("Beograd");
		o2.setIme("Mitar");
		o2.setNovac(600);
		o2.setPrezime("Miric");
		osobaService.save(o2);
		
		
		Artikli a1=new Artikli();
		a1.setCena(150.30);
		a1.setNaziv("Brasno 1kg");
		a1.setOsoba(o1);
		artikliService.save(a1);
		
		Art art1=new Art();
		art1.setCena(150.30);
		art1.setNaziv("Brasno 1kg");
		artService.save(art1);
		
		Art art2=new Art();
		art2.setCena(30.30);
		art2.setNaziv("Margarin 1kg");
		artService.save(art2);
		
		Art art3=new Art();
		art3.setCena(20.30);
		art3.setNaziv("Faks 1kg");
		artService.save(art3);
		
		Art art4=new Art();
		art4.setCena(70.30);
		art4.setNaziv("Piroska 200g");
		artService.save(art4);
	}
		
	
}
