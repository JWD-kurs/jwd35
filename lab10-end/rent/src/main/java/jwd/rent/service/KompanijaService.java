package jwd.rent.service;

import java.util.List;

import jwd.rent.model.Kompanija;


public interface KompanijaService {
	List<Kompanija> findAll();
	Kompanija findOne(Long id);
	void save(Kompanija kompanija);
	void remove(Long id);

}
