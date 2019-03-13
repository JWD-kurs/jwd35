package jwd.rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.rent.model.Kompanija;

@Repository
public interface KompanijaRepository 
	extends JpaRepository<Kompanija, Long> {

}
