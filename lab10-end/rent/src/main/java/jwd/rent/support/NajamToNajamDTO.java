package jwd.rent.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.rent.model.Najam;
import jwd.rent.web.dto.NajamDTO;

@Component
public class NajamToNajamDTO implements Converter<Najam, NajamDTO> {

	@Override
	public NajamDTO convert(Najam arg0) {
		
		NajamDTO dto = new NajamDTO();
		dto.setId(arg0.getId());
		
		return dto;
	}

}
