package jwd.rent;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.rent.model.Automobil;
import jwd.rent.model.Kompanija;
import jwd.rent.service.AutomobilService;
import jwd.rent.service.KompanijaService;


@Component
public class TestData {

	@Autowired
	private KompanijaService kompanijaService;
	
	@Autowired
	private AutomobilService automobilService;
	
	@PostConstruct
	public void init() {
		
		Kompanija k1 = new Kompanija();
		k1.setNaziv("SuRent");
		k1.setAdresa("Balzakova 1");
		k1.setTelefon("024/151-363");
		kompanijaService.save(k1);
		
		Kompanija k2 = new Kompanija();
		k2.setNaziv("NSRent");
		k2.setAdresa("Maksima Gorkog 2");
		k2.setTelefon("021/4141-515");
		kompanijaService.save(k2);
		
		Automobil a1 = new Automobil();
		a1.setModel("Nissan Prairie");
		a1.setRegistracija("SU82404");
		a1.setGodiste(1991);
		a1.setPotrosnja(10.3);
		a1.setKompanija(k1);
		
		automobilService.save(a1);
	}
}