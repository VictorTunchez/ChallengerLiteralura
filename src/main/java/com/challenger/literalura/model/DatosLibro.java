package com.challenger.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,

        @JsonAlias("authors") List<DatosAutor> autor,

        @JsonAlias("languages") List<String> idiomas,

        @JsonAlias("download_count") Integer totalDescargas

) {
}
