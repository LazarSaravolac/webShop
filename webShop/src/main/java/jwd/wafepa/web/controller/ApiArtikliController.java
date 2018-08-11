package jwd.wafepa.web.controller;

import java.util.ArrayList;
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

import jwd.wafepa.model.Artikli;
import jwd.wafepa.service.ArtikliService;
import jwd.wafepa.service.OsobaService;
import jwd.wafepa.support.ArtikliDTOtoArtikli;
import jwd.wafepa.support.ArtikliToArtikliDTO;
import jwd.wafepa.web.dto.ArtikliDTO;

@RestController
@RequestMapping("/api/artikli")
public class ApiArtikliController {


	@Autowired
	OsobaService osobaService;
	
	@Autowired
	ArtikliService artikliService;
	
	@Autowired
	private ArtikliToArtikliDTO toArtikliDTO;
	
	@Autowired
	private ArtikliDTOtoArtikli toArtikli;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ArtikliDTO>> get(
			@RequestParam(required=false) String naziv,
			@RequestParam(required=false) Long idOsobe,
			@RequestParam(defaultValue="0") int page){
		
		
		Page<Artikli>artikli;
		if(naziv!=null || idOsobe!=null) {
			artikli=artikliService.pretraga(naziv,idOsobe, page);
		}else {
			artikli=artikliService.findAll(page);
		}
		
		
		
		
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(toArtikliDTO.convert(artikli.getContent()),headers,HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET,
			value="/{id}")
public ResponseEntity<ArtikliDTO> get(@PathVariable Long id){
		
		Artikli artikli=artikliService.findOne(id);
		
		if(artikli==null) {
			return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toArtikliDTO.convert(artikli),HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ArtikliDTO> add(
			@Validated @RequestBody ArtikliDTO novProizvod){
		
		Artikli artikli=toArtikli.convert(novProizvod);
		artikliService.save(artikli);
		
		return new ResponseEntity<>(toArtikliDTO.convert(artikli),HttpStatus.CREATED);
		
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}")
	public ResponseEntity<ArtikliDTO> edit(
			@PathVariable Long id,
			@Validated @RequestBody ArtikliDTO izmenjen){
		
		if(!id.equals(izmenjen.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Artikli ucesnik=toArtikli.convert(izmenjen);
		artikliService.save(ucesnik);
		
		return new ResponseEntity<>(toArtikliDTO.convert(ucesnik),HttpStatus.OK);
	}
	
	
	
	@RequestMapping(method=RequestMethod.DELETE,
			value="/{id}")
	public ResponseEntity<ArtikliDTO> delete(@PathVariable Long id){
		artikliService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
