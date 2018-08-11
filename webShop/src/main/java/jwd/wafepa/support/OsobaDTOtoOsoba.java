package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Artikli;
import jwd.wafepa.model.Osoba;
import jwd.wafepa.service.ArtikliService;
import jwd.wafepa.service.OsobaService;
import jwd.wafepa.web.dto.ArtikliDTO;
import jwd.wafepa.web.dto.OsobaDTO;

@Component
public class OsobaDTOtoOsoba implements Converter<OsobaDTO, Osoba> {

	@Autowired
	ArtikliService artikliService;
	
	@Autowired
	OsobaService osobaService;

	@Override
	public Osoba convert(OsobaDTO dto) {
		// TODO Auto-generated method stub
		Osoba o=new Osoba();
		o.setId(dto.getId());
		o.setIme(dto.getIme());
		o.setPrezime(dto.getPrezime());
		o.setNovac(dto.getNovac());
		o.setGrad(dto.getGrad());
		return o;
	}
	
	public List<Osoba>convert(List<OsobaDTO>dto){
		List<Osoba>osobe=new ArrayList<>();
		for(OsobaDTO a:dto) {
			osobe.add(convert(a));
		}
		return osobe;
	}
	
	
	
	

}
