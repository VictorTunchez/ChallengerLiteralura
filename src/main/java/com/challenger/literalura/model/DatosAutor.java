package com.challenger.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        @JsonAlias("birth_year") String anioNacimiento,

        @JsonAlias("death_year") String anioMuerte,

        @JsonAlias("name") String nombre

){
}
