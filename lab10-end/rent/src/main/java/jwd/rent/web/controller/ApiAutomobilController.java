package jwd.rent.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.rent.model.Automobil;
import jwd.rent.model.Najam;
import jwd.rent.service.AutomobilService;
import jwd.rent.service.NajamService;
import jwd.rent.support.AutomobilDTOToAutomobil;
import jwd.rent.support.AutomobilToAutomobilDTO;
import jwd.rent.support.NajamToNajamDTO;
import jwd.rent.web.dto.AutomobilDTO;
import jwd.rent.web.dto.NajamDTO;

@RestController
@RequestMapping("/api/automobili")
public class ApiAutomobilController {
	@Autowired
	private AutomobilService automobilService;
	@Autowired
	private AutomobilToAutomobilDTO toAutomobilDTO;
	@Autowired
	private NajamToNajamDTO toNajamDTO;
	@Autowired
	private AutomobilDTOToAutomobil toAutomobil;
	@Autowired
	private NajamService najamService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<AutomobilDTO>> get(
			@RequestParam(required=false) String model,
			@RequestParam(required=false) Integer godiste,
			@RequestParam(required=false) Double potrosnja,
			@RequestParam(defaultValue="0") int pageNum){
		
		Page<Automobil> automobili;
		
		if(model != null || godiste != null || potrosnja != null) {
			automobili = automobilService.pretraga(model, godiste, potrosnja, pageNum);
		}else{
			automobili = automobilService.findAll(pageNum);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(automobili.getTotalPages()) );
		return  new ResponseEntity<>(
				toAutomobilDTO.convert(automobili.getContent()),
				headers,
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET,
					value="/{id}")
	public ResponseEntity<AutomobilDTO> get(
			@PathVariable Long id){
		Automobil automobil = automobilService.findOne(id);
		
		if(automobil==null){
			return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toAutomobilDTO.convert(automobil),
				HttpStatus.OK);	
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<AutomobilDTO> add(
			@Validated @RequestBody AutomobilDTO novAutomobil){
		
		Automobil automobil = toAutomobil.convert(novAutomobil); 
		automobilService.save(automobil);
		
		return new ResponseEntity<>(toAutomobilDTO.convert(automobil),
				HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}")
	public ResponseEntity<AutomobilDTO> edit(
			@PathVariable Long id,
			@Validated @RequestBody AutomobilDTO izmenjen){
		
		if(!id.equals(izmenjen.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Automobil automobil = toAutomobil.convert(izmenjen); 
		automobilService.save(automobil);
		
		return new ResponseEntity<>(toAutomobilDTO.convert(automobil),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,
			value="/{id}")
	public ResponseEntity<AutomobilDTO> delete(@PathVariable Long id){
		
		Automobil deleted = automobilService.remove(id);
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toAutomobilDTO.convert(deleted),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{id}/iznajmljivanje")
	public ResponseEntity<NajamDTO> rent(@PathVariable Long id){
		
		Najam n = najamService.rentACar(id);
		
		if(n != null) {
			return new ResponseEntity<>(toNajamDTO.convert(n), HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ExceptionHandler
	public ResponseEntity<Void> validationHandler(
					DataIntegrityViolationException e){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
