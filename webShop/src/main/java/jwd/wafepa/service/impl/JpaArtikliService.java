package jwd.wafepa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Artikli;
import jwd.wafepa.repository.ArtikliRepository;
import jwd.wafepa.service.ArtikliService;

@Service
public class JpaArtikliService implements ArtikliService{

	@Autowired
	ArtikliRepository artikliRepository;

	@Override
	public Artikli save(Artikli artikli) {
		// TODO Auto-generated method stub
		return artikliRepository.save(artikli);
	}

	@Override
	public Page<Artikli> findAll(int page) {
		// TODO Auto-generated method stub
		return artikliRepository.findAll(new PageRequest(page, 51));
	}

	@Override
	public Artikli findOne(Long id) {
		// TODO Auto-generated method stub
		return artikliRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		artikliRepository.delete(id);
	}

	@Override
	public Page<Artikli> findByOsobaId(int pageNum, Long osobaId) {
		// TODO Auto-generated method stub
		return artikliRepository.findByOsobaId(osobaId, new PageRequest(pageNum, 51));
	}

	@Override
	public Page<Artikli> pretraga(String naziv,Long idOsobe, int page) {
		// TODO Auto-generated method stub
		return artikliRepository.pretraga(naziv,idOsobe, new PageRequest(page, 51));
	}
}
