package jwd.rent.service;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import jwd.rent.model.Automobil;

public interface AutomobilService {
	Page<Automobil> findAll(int pageNum);
	Automobil findOne(Long id);
	void save(Automobil automobil);
	Automobil remove(Long id);
	Page<Automobil> findByKompanijaId(int pageNum, Long kompanijaId);
	Page<Automobil> pretraga(
			@Param("model") String model, 
			@Param("godiste") Integer godiste, 
			@Param("potrosnja") Double potrosnja,
			int page);
}
