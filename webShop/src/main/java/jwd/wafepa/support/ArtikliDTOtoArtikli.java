package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Artikli;
import jwd.wafepa.service.ArtikliService;
import jwd.wafepa.service.OsobaService;
import jwd.wafepa.web.dto.ArtikliDTO;

@Component
public class ArtikliDTOtoArtikli  implements Converter<ArtikliDTO, Artikli> {

	@Autowired
	ArtikliService artikliService;
	
	@Autowired
	OsobaService osobaService;
	
	
	
	@Override
	public Artikli convert(ArtikliDTO dto) {
		// TODO Auto-generated method stub
		Artikli a=new Artikli();
		if(dto.getId()==null) {
			a.setOsoba(osobaService.findOne(dto.getOsobaId()));
		}else {
			a=artikliService.findOne(dto.getId());
		}
		
		a.setCena(dto.getCena());
		a.setId(dto.getId());
		a.setNaziv(dto.getNaziv());
		
		return a;
	}
	
	public List<Artikli>convert(List<ArtikliDTO>d){
		List<Artikli>a=new ArrayList<>();
		for(ArtikliDTO s:d) {
			a.add(convert(s));
		}
		return a;
	}

}
