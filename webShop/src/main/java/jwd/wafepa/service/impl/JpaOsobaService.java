package jwd.wafepa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Artikli;
import jwd.wafepa.model.Osoba;
import jwd.wafepa.repository.ArtikliRepository;
import jwd.wafepa.repository.OsobaRepository;
import jwd.wafepa.service.OsobaService;

@Service
public class JpaOsobaService implements OsobaService{

	@Autowired
	OsobaRepository osobaRepository;
	
	@Autowired
	ArtikliRepository artikliRepository;
	
	@Override
	public List<Osoba> findAll() {
		// TODO Auto-generated method stub
		
		return osobaRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		osobaRepository.delete(id);
	}

	@Override
	public Osoba save(Osoba osoba) {
		// TODO Auto-generated method stub
		return osobaRepository.save(osoba);
	}

	@Override
	public Osoba findOne(Long id) {
		// TODO Auto-generated method stub
		return osobaRepository.findOne(id);
	}

	@Override
	public void kupi(Long osobaId, String artikalId) {
		// TODO Auto-generated method stub
		String izvuci[]=artikalId.split("Y");
		
		
		Double cena=Double.parseDouble(izvuci[2]);
		String naziv=izvuci[1];
		Osoba osoba=osobaRepository.findOne(osobaId);
		if(osoba.getNovac()>cena) {
			osoba.setNovac((int) (osoba.getNovac()-cena));
			Artikli a1=new Artikli();
			a1.setCena(cena);
			a1.setNaziv(naziv);
			a1.setOsoba(osoba);
			osobaRepository.save(osoba);
			artikliRepository.save(a1);
		}
	}

	@Override
	public boolean prenos(Long osoba1, Long osoba2,Integer iznos) {
		// TODO Auto-generated method stub
		Osoba o1=osobaRepository.findOne(osoba1);
		boolean rez=false;
		if(o1.getNovac()>iznos) {
			Osoba o2=osobaRepository.findOne(osoba2);
			o2.setNovac(o2.getNovac()+iznos);
			osobaRepository.save(o2);
			o1.setNovac(o1.getNovac()-iznos);
			osobaRepository.save(o1);
			rez=true;
		}else {
			rez=false;
		}
		
		return rez;
	}

	@Override
	public Page<Osoba> findAll(int page) {
		// TODO Auto-generated method stub
		return osobaRepository.findAll(new PageRequest(page, 5));
	}

	@Override
	public Page<Osoba> pretraga(String ime, int page) {
		// TODO Auto-generated method stub
		if(ime!=null) {
			ime=ime + "%";
		}
		return osobaRepository.pretraga(ime, new PageRequest(page, 5));
	}

}
