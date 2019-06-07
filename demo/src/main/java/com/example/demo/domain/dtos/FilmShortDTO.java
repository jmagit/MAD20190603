package com.example.demo.domain.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@ApiModel(value = "PeliculaCorto", description = "Version corta de las peliculas")
@Data @AllArgsConstructor @NoArgsConstructor
public class FilmShortDTO {
	@ApiModelProperty(value = "Identificador de la pelicula", required = true, accessMode = AccessMode.READ_ONLY)
	private int filmId;
	@ApiModelProperty(value = "Titulo de la pelicula", required = true)
	private String title;
}
