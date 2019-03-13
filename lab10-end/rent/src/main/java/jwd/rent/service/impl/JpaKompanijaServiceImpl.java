package jwd.rent.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.rent.model.Kompanija;
import jwd.rent.repository.KompanijaRepository;
import jwd.rent.service.KompanijaService;

@Service
@Transactional
public class JpaKompanijaServiceImpl implements KompanijaService {
	@Autowired
	private KompanijaRepository kompanijaRepository;

	@Override
	public List<Kompanija> findAll() {
		return kompanijaRepository.findAll();
	}

	@Override
	public Kompanija findOne(Long id) {
		return kompanijaRepository.findOne(id);
	}

	@Override
	public void save(Kompanija kompanija) {
		kompanijaRepository.save(kompanija);
	}

	@Override
	public void remove(Long id) {
		kompanijaRepository.delete(id);
	}
}
