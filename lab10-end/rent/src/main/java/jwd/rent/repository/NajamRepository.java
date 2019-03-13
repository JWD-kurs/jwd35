package jwd.rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.rent.model.Najam;

@Repository
public interface NajamRepository extends JpaRepository<Najam, Long>{

}
