package com.challenger.literalura.repository;

import com.challenger.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IAutorInterface extends JpaRepository<Autor, Long>{
     List<Autor> findAll();
}
