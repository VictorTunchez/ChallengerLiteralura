package com.challenger.literalura.repository;

import com.challenger.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ILibroRepository extends JpaRepository<Libro, Long> {
    boolean existsByTitulo(String titulo);

    @Query(value = "SELECT * FROM libros WHERE LOWER(:idioma) = ANY (SELECT LOWER(unaccent(i)) FROM unnest(idiomas) i)", nativeQuery = true)
    List<Libro> buscarLibroPorIdioma(@Param("idioma") String idioma);
}
