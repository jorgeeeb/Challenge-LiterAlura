package com.aluracursos.Literalura.repository;

import com.aluracursos.Literalura.model.Autor;
import com.aluracursos.Literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends CrudRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a LEFT JOIN FETCH a.libros")
    List<Autor> findAllWithLibros();

    @Query("SELECT a FROM Autor a JOIN FETCH a.libros WHERE a.añoNacimiento <= :año AND a.añoFallecimiento >= :año")
    List<Autor> findAutoresVivosEnAño(int año);
}
