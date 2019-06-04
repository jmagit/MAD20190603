package com.example.demo.domain.dtos;

import java.io.Serializable;

import com.example.demo.domain.entities.City;
import com.example.demo.domain.entities.Country;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data @AllArgsConstructor @NoArgsConstructor
public class CityDTO implements Serializable {
	private int cityId;
	private String city;
	private Integer countryId;
	
	public static CityDTO from(City source) {
		return new CityDTO(
				source.getCityId(),
				source.getCity(),
				source.getCountry() == null ? null : source.getCountry().getCountryId()
				);
	}
	public static City from(CityDTO source) {
		return new City(
				source.getCityId(),
				source.getCity(),
				source.getCountryId() == null ? null : new Country(source.getCountryId())
				);
	}

}
