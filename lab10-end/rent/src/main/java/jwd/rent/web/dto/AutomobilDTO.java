package jwd.rent.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class AutomobilDTO {

	private Long id;
	@NotBlank()
	private String model;
	private String registracija;
	@Min(1800)
	@Max(3000)
	private Integer godiste;
	@Min(0)
	@Max(99)
	private Double potrosnja;
	private Boolean iznajmljen;
	private Long kompanijaId;
	private String kompanijaNaziv;
	
	
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
	public Long getKompanijaId() {
		return kompanijaId;
	}
	public void setKompanijaId(Long kompanijaId) {
		this.kompanijaId = kompanijaId;
	}
	public String getKompanijaNaziv() {
		return kompanijaNaziv;
	}
	public void setKompanijaNaziv(String kompanijaNaziv) {
		this.kompanijaNaziv = kompanijaNaziv;
	}
}
