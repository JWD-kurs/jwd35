package jwd.rent.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.rent.model.Automobil;
import jwd.rent.model.Kompanija;
import jwd.rent.service.AutomobilService;
import jwd.rent.service.KompanijaService;
import jwd.rent.support.AutomobilToAutomobilDTO;
import jwd.rent.support.KompanijaToKompanijaDTO;
import jwd.rent.web.dto.AutomobilDTO;
import jwd.rent.web.dto.KompanijaDTO;

@RestController
@RequestMapping(value="/api/kompanije")
public class ApiKompanijaController {
	@Autowired
	private KompanijaService kompanijaService;
	@Autowired
	private AutomobilService automobilService;
	@Autowired
	private KompanijaToKompanijaDTO toDTO;
	@Autowired
	private AutomobilToAutomobilDTO toAutomobilDTO;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<KompanijaDTO>> get(){
		return new ResponseEntity<>(
				toDTO.convert(kompanijaService.findAll()),
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<KompanijaDTO> get(
			@PathVariable Long id){
		
		Kompanija kompanija = kompanijaService.findOne(id);
		
		if(kompanija == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(kompanija),
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{komoanijaId}/automobili")
	public ResponseEntity<List<AutomobilDTO>> knjigeIzdavac(
			@PathVariable Long kompanijaId,
			@RequestParam(defaultValue="0") int pageNum){
		Page<Automobil> automobili = automobilService.findByKompanijaId(pageNum, kompanijaId);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(automobili.getTotalPages()) );
		return  new ResponseEntity<>(
				toAutomobilDTO.convert(automobili.getContent()),
				headers,
				HttpStatus.OK);
	}
}
