package jwd.rent.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Automobil {
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column(nullable=false)
	private String model;
	@Column(nullable=false, unique=true)
	private String registracija;
	@Column(nullable=false)
	private Integer godiste;
	@Column
	private Double potrosnja;
	@Column
	private Boolean iznajmljen = false;
	@ManyToOne(fetch=FetchType.EAGER)
	private Kompanija kompanija;
	@OneToMany(mappedBy="automobil",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Najam> najmovi = new ArrayList<>();

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getRegistracija() {
		return registracija;
	}
	public void setRegistracija(String registracija) {
		this.registracija = registracija;
	}
	public Integer getGodiste() {
		return godiste;
	}
	public void setGodiste(Integer godiste) {
		this.godiste = godiste;
	}
	public Double getPotrosnja() {
		return potrosnja;
	}
	public void setPotrosnja(Double potrosnja) {
		this.potrosnja = potrosnja;
	}
	public Boolean getIznajmljen() {
		return iznajmljen;
	}
	public void setIznajmljen(Boolean iznajmljen) {
		this.iznajmljen = iznajmljen;
	}
	public Kompanija getKompanija() {
		return kompanija;
	}
	public void setKompanija(Kompanija kompanija) {
		this.kompanija = kompanija;
		if(kompanija!=null && !kompanija.getAutomobili().contains(this)){
			kompanija.getAutomobili().add(this);
		}
	}
	public List<Najam> getNajmovi() {
		return najmovi;
	}
	public void setNajmovi(List<Najam> najmovi) {
		this.najmovi = najmovi;
	}
	public void addNajam(Najam najam){
		this.najmovi.add(najam);
		
		if(!this.equals(najam.getAutomobil())){
			najam.setAutomobil(this);
		}
	}
	
}
