package com.alura.literalura.repository;


import com.alura.literalura.model.Autor;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {
     Optional<Autor> findByAutor(String autor);



     @Query("SELECT a FROM Autor a WHERE a.nacimiento <= :anoDeEvaluacion AND a.muerte >= :anoDeEvaluacion")
     List<Autor> autoresVivosEnUnPeriodo(int anoDeEvaluacion);

     @Query("SELECT a FROM Autor a JOIN a.libro l WHERE l.idioma ILIKE %:idioma")
     List<Autor> busquedaPorIdioma(String idioma);

}
