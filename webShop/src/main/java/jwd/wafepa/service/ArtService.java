package jwd.wafepa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import jwd.wafepa.model.Art;


public interface ArtService {
	Page<Art>findAll(int page);
	Art save(Art art);
	Art findOne(Long id);
	Page<Art> pretraga(
			@Param("naziv") String naziv, 
			int page);
	void delete(Long id);
}
