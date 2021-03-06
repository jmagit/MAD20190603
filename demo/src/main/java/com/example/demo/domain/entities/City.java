package com.example.demo.domain.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * The persistent class for the city database table.
 * 
 */
@Entity
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "city_id")
	private int cityId;

	@NotBlank
	@Size(max = 50, min = 2)
	private String city;

	@Column(name = "last_update")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")

	private Timestamp lastUpdate;

	// bi-directional many-to-one association to Address
	@OneToMany(mappedBy = "city")
	@Valid
	@JsonIgnore
	private List<Address> addresses;

	// bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name = "country_id")
	@NotNull
	@JsonIgnore
	private Country country;

	public City() {
	}

	public City(int cityId, String city, Country country) {
		this.cityId = cityId;
		this.city = city;
		this.country = country;
	}

	public int getCityId() {
		return this.cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Address addAddress(Address address) {
		getAddresses().add(address);
		address.setCity(this);

		return address;
	}

	public Address removeAddress(Address address) {
		getAddresses().remove(address);
		address.setCity(null);

		return address;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cityId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (cityId != other.cityId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", city=" + city + "]";
	}

	@Autowired
//	@Transient
	private static Validator validator;

	public Set<ConstraintViolation<City>> validate() {
		return validator.validate(this);
	}
	@JsonIgnore
	public boolean isValid() {
		return validate().size() == 0;
	}
	public boolean notIsValid() {
		return !isValid();
	}

}