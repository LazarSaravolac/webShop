package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.Art;
import jwd.wafepa.model.Artikli;
import jwd.wafepa.model.Osoba;
import jwd.wafepa.service.ArtikliService;
import jwd.wafepa.service.OsobaService;
import jwd.wafepa.support.ArtikliToArtikliDTO;
import jwd.wafepa.support.OsobaDTOtoOsoba;
import jwd.wafepa.support.OsobaToOsobaDTO;
import jwd.wafepa.web.dto.ArtikliDTO;
import jwd.wafepa.web.dto.OsobaDTO;

@RestController
@RequestMapping(value="/api/osobe")
public class ApiOsobaController {

	
	@Autowired
	OsobaService osobaService;
	
	@Autowired
	ArtikliService artikliService;
	
	@Autowired
	private OsobaToOsobaDTO toDTO;
	
	@Autowired
	private OsobaDTOtoOsoba toOsoba;
	
	@Autowired
	private ArtikliToArtikliDTO toArtikliDTO;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<OsobaDTO>> get(	@RequestParam(required=false) String ime,
			@RequestParam(defaultValue="0") int page){
		
		
Page<Osoba>osobe;
		
		if(ime!=null ) {
			osobe=osobaService.pretraga(ime, page);
		}else {
			osobe=osobaService.findAll(page);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", String.valueOf(osobe.getTotalPages()));
		return new ResponseEntity<>(
				toDTO.convert(osobe.getContent()),headers,
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<OsobaDTO> add(
			@RequestBody OsobaDTO novProizvod){
		
		Osoba osoba=toOsoba.convert(novProizvod);
		osobaService.save(osoba);
		
		return new ResponseEntity<>(toDTO.convert(osoba),HttpStatus.CREATED);
		
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{osobaId}/{artikalId}")
	public ResponseEntity<OsobaDTO> odigraj(@PathVariable Long osobaId,@PathVariable String artikalId){
		osobaService.kupi(osobaId, artikalId);
		Osoba osoba=osobaService.findOne(osobaId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("ime", osoba.getIme() + " " +  osoba.getPrezime());
		return new ResponseEntity<>(headers,HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{o1}/{o2}/{iznos}")
	public ResponseEntity<OsobaDTO> odigraj(@PathVariable Long o1,@PathVariable Long o2,@PathVariable Integer iznos){
		boolean e=osobaService.prenos(o1, o2, iznos);
		if(e) {
			System.out.println("Uspesno");
			return new ResponseEntity<>(HttpStatus.OK);	
		}else {
			System.out.println("Ne uspesno");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{artikliId}/osobe")
	public ResponseEntity<List<ArtikliDTO>> knjigeIzdavac(
			@PathVariable Long artikliId,
			@RequestParam(defaultValue="0") int page){
		
		Page<Artikli>artikli=artikliService.findByOsobaId(page, artikliId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(artikli.getTotalPages()) );
		return  new ResponseEntity<>(
				toArtikliDTO.convert(artikli.getContent()),
				headers,
				HttpStatus.OK);
		
	}
}
