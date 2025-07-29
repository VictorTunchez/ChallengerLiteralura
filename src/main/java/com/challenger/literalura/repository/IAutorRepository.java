package com.challenger.literalura.repository;

import com.challenger.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface IAutorRepository extends JpaRepository<Autor, Long>{
     List<Autor> findAll();
     List<Autor> findByAnioMuerteLessThan(int anio);

     Optional<Autor> findByNombre(String nombre);
}
