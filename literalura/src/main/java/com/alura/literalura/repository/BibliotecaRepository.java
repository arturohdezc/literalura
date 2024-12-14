package com.alura.literalura.repository;

import com.alura.literalura.model.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BibliotecaRepository extends JpaRepository <Biblioteca,Long> {


}
