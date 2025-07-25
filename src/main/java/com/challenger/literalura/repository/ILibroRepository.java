package com.challenger.literalura.repository;

import com.challenger.literalura.model.Autor;
import com.challenger.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ILibroRepository extends JpaRepository<Libro, Long> {
    boolean existsByTitulo(String titulo);
}
