package jwd.rent.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.rent.model.Automobil;
import jwd.rent.web.dto.AutomobilDTO;

@Component
public class AutomobilToAutomobilDTO 
	implements Converter<Automobil, AutomobilDTO> {

	@Override
	public AutomobilDTO convert(Automobil source) {
		AutomobilDTO dto = new AutomobilDTO();
		dto.setId(source.getId());
		dto.setModel(source.getModel());
		dto.setRegistracija(source.getRegistracija());
		dto.setGodiste(source.getGodiste());
		dto.setPotrosnja(source.getPotrosnja());
		dto.setIznajmljen(source.getIznajmljen());
		dto.setKompanijaId(source.getKompanija().getId());
		dto.setKompanijaNaziv(source.getKompanija().getNaziv());
		
		return dto;
	}
	
	public List<AutomobilDTO> convert(List<Automobil> automobili){
		List<AutomobilDTO> ret = new ArrayList<>();
		
		for(Automobil a : automobili){
			ret.add(convert(a));
		}
		
		return ret;
	}

}
