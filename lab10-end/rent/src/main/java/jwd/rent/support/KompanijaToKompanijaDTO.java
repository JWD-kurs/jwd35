package jwd.rent.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.rent.model.Kompanija;
import jwd.rent.web.dto.KompanijaDTO;

@Component
public class KompanijaToKompanijaDTO 
	implements Converter<Kompanija, KompanijaDTO> {

	@Override
	public KompanijaDTO convert(Kompanija kompanija) {
		KompanijaDTO kompanijaDTO = new KompanijaDTO();
		kompanijaDTO.setId(kompanija.getId());
		kompanijaDTO.setNaziv(kompanija.getNaziv());
		kompanijaDTO.setAdresa(kompanija.getAdresa());
		kompanijaDTO.setTelefon(kompanija.getTelefon());
		return kompanijaDTO;
	}

	public List<KompanijaDTO> convert(List<Kompanija> kompanije) {
		List<KompanijaDTO> ret = new ArrayList<>();
		
		for(Kompanija k: kompanije){
			ret.add(convert(k));
		}
		
		return ret;
	}
}
