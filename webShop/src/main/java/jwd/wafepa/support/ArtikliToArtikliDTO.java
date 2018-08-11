package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Artikli;
import jwd.wafepa.web.dto.ArtikliDTO;

@Component
public class ArtikliToArtikliDTO  implements Converter<Artikli,ArtikliDTO>{

	@Override
	public ArtikliDTO convert(Artikli artikli) {
		// TODO Auto-generated method stub
		ArtikliDTO dto=new ArtikliDTO();
		dto.setCena(artikli.getCena());
		dto.setId(artikli.getId());
		dto.setNaziv(artikli.getNaziv());
		dto.setOsobaId(artikli.getOsoba().getId());
		dto.setOsobaNaziv(artikli.getOsoba().getIme());
		return dto;
	}
	
	public List<ArtikliDTO>convert(List<Artikli>u){
		List<ArtikliDTO>dto=new ArrayList<>();
		for(Artikli a:u) {
			dto.add(convert(a));
		}
		return dto;
	}

}
