package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.Art;
import jwd.wafepa.model.Osoba;
import jwd.wafepa.service.ArtService;
import jwd.wafepa.web.dto.OsobaDTO;



@RestController
@RequestMapping("/api/art")
public class ApiArtController {

	@Autowired
	ArtService artService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Art>> get(
	@RequestParam(required=false) String naziv,
	@RequestParam(defaultValue="0") int page){
		Page<Art>artikli;
		
		if(naziv!=null ) {
			artikli=artService.pretraga(naziv, page);
		}else {
			artikli=artService.findAll(page);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", String.valueOf(artikli.getTotalPages()));
		return new ResponseEntity<>(
				artikli.getContent(),headers,
				HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<Art> getFestival(@PathVariable Long id) {
		Art artikl = artService.findOne(id);
		if (artikl == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(artikl, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}")
	public ResponseEntity<Art> edit(
			@PathVariable Long id,
			@RequestBody Art izmenjen){
		
		if(!id.equals(izmenjen.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		
		artService.save(izmenjen);
		
		return new ResponseEntity<>(izmenjen,
				HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Art> add(
			@RequestBody Art novProizvod){
		
		Art e=novProizvod;
		artService.save(e);
		
		return new ResponseEntity<>(e,HttpStatus.CREATED);
		
	}
	@RequestMapping(method=RequestMethod.DELETE,
			value="/{id}")
	public ResponseEntity<Art> delete(@PathVariable Long id){
		artService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
