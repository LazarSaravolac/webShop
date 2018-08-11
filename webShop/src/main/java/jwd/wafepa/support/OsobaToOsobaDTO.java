package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Osoba;
import jwd.wafepa.web.dto.OsobaDTO;

@Component
public class OsobaToOsobaDTO  implements Converter<Osoba, OsobaDTO>{

	@Override
	public OsobaDTO convert(Osoba osoba) {
		OsobaDTO dto=new OsobaDTO();
		dto.setGrad(osoba.getGrad());
		dto.setId(osoba.getId());
		dto.setIme(osoba.getIme());
		dto.setPrezime(osoba.getPrezime());
		dto.setNovac(osoba.getNovac());
		return dto;
	}

	public List<OsobaDTO>convert(List<Osoba>o){
		List<OsobaDTO>dto=new ArrayList<>();
		for(Osoba a:o) {
			dto.add(convert(a));
		}
		return dto;
	}
}
