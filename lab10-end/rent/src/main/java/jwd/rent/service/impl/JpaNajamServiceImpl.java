package jwd.rent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.rent.model.Automobil;
import jwd.rent.model.Najam;
import jwd.rent.repository.AutomobilRepository;
import jwd.rent.repository.NajamRepository;
import jwd.rent.service.NajamService;

@Service
public class JpaNajamServiceImpl implements NajamService{
	
	@Autowired
	private NajamRepository najamRepository;
	@Autowired
	private AutomobilRepository automobilRepository;
	
	@Override
	public Najam rentACar(Long automobilId) {
		
		if(automobilId == null) {
			throw new IllegalArgumentException("Id of a car cannot be null!");
		}
		
		Automobil automobil = automobilRepository.findOne(automobilId);
		if(automobil == null) {
			throw new IllegalArgumentException("There is no car with given id!");
		}
		
		if(!automobil.getIznajmljen()) {
			automobil.setIznajmljen(true);
			
			Najam najam = new Najam();
			najam.setAutomobil(automobil);
			
			najamRepository.save(najam);
			automobilRepository.save(automobil);
				
			return najam;
		}
		
		return null;
	}
}
