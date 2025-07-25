package com.challenger.literalura.repository;

import com.challenger.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILibroRepository extends JpaRepository<Libro, Long> {

}
