package jwd.rent.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.rent.model.Automobil;
import jwd.rent.model.Kompanija;
import jwd.rent.service.AutomobilService;
import jwd.rent.service.KompanijaService;
import jwd.rent.web.dto.AutomobilDTO;

@Component
public class AutomobilDTOToAutomobil 
	implements Converter<AutomobilDTO, Automobil>{
	
	@Autowired
	private AutomobilService automobilService;
	@Autowired
	private KompanijaService kompanijaService;
	
	
	@Override
	public Automobil convert(AutomobilDTO source) {
		Automobil automobil;
		Kompanija k = kompanijaService.findOne(source.getKompanijaId());
		
		if(source.getId()==null){
			automobil = new Automobil();
		}else{
			automobil = automobilService.findOne(source.getId());
		}
		
		automobil.setKompanija(k);
		
		automobil.setModel(source.getModel());
		automobil.setRegistracija(source.getRegistracija());
		automobil.setGodiste(source.getGodiste());
		automobil.setPotrosnja(source.getPotrosnja());
		automobil.setIznajmljen(source.getIznajmljen());
		
		return automobil;
	}

}
