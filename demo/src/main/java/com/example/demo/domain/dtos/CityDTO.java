package com.example.demo.domain.dtos;

import java.io.Serializable;

import com.example.demo.domain.entities.City;
import com.example.demo.domain.entities.Country;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@ApiModel(value = "Editor de Ciudad", description = "Version editable de las ciudades")
@Data @AllArgsConstructor @NoArgsConstructor
public class CityDTO implements Serializable {
	@ApiModelProperty(value = "Identificador de la ciudad", required = true, accessMode = AccessMode.READ_ONLY)
	private int cityId;
	@ApiModelProperty(value = "Nombre de la ciudad", required = true)
	private String city;
	@ApiModelProperty(value = "Identificador del pais", required = true, notes = "Debe existir ...")
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
