package jwd.wafepa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import jwd.wafepa.model.Art;
import jwd.wafepa.model.Osoba;

public interface OsobaService {

	List<Osoba>findAll();
	void delete(Long id);
	void kupi(Long osobaId,String artikalId);
	boolean prenos(Long osoba1,Long osoba2,Integer iznos);
	Osoba save(Osoba osoba);
	Osoba findOne(Long id);
	Page<Osoba>findAll(int page);
	Page<Osoba> pretraga(
			@Param("ime") String ime, 
			int page);
}
