package jwd.rent.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Kompanija {
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	private String naziv;
	@Column
	private String adresa;
	@Column
	private String telefon;	
	@OneToMany(mappedBy="kompanija",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Automobil> automobili = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public List<Automobil> getAutomobili(){
		return this.automobili;
	}
	public void setAutomobili(List<Automobil> automobili) {
		this.automobili = automobili;
	}
	public void addAutomobil(Automobil automobil){
		this.automobili.add(automobil);
		
		if(!this.equals(automobil.getKompanija())){
			automobil.setKompanija(this);
		}
	}
}
