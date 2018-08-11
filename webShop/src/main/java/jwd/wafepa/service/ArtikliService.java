package jwd.wafepa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import jwd.wafepa.model.Artikli;

public interface ArtikliService {
	Artikli save(Artikli artikli);
	Page<Artikli>findAll(int page);
	Artikli findOne(Long id);
	void delete(Long id);
	Page<Artikli> findByOsobaId(int pageNum, Long osobaId);
	Page<Artikli> pretraga(
			@Param("naziv") String naziv, 
			@Param("idOsobe") Long idOsobe,
			int page);
}
