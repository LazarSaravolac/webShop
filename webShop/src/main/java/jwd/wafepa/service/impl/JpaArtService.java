package jwd.wafepa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Art;
import jwd.wafepa.repository.ArtRepository;
import jwd.wafepa.service.ArtService;

@Service
public class JpaArtService implements ArtService{

	@Autowired
	ArtRepository artRepo;
	
	@Override
	public Page<Art> findAll(int page) {
		// TODO Auto-generated method stub
		return artRepo.findAll(new PageRequest(page, 3));
	}

	@Override
	public Art save(Art art) {
		// TODO Auto-generated method stub
		return artRepo.save(art);
	}

	@Override
	public Art findOne(Long id) {
		// TODO Auto-generated method stub
		return artRepo.findOne(id);
	}

	@Override
	public Page<Art> pretraga(String naziv, int page) {
		// TODO Auto-generated method stub
		if(naziv!=null) {
			naziv="%" + naziv +"%";
		}
		return artRepo.pretraga(naziv, new PageRequest(page, 3));
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		artRepo.delete(id);
	}

}
