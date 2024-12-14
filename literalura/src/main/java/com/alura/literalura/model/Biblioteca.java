package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "biblioteca")
public class Biblioteca {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdDB;
    @Column(unique = true)
    private String titulo;
    private String id;
    private List<String> idioma;
    private Integer numeroDeDescargas;
    private String autor;
    private Integer nacimiento;
    private Integer muerte;


    public Biblioteca(){}

    public Biblioteca(DatosLibro datosLibro, DatosAutor datosAutor) {
        this.id = datosLibro.id();


        if (datosLibro.titulo().length() > 255) {
                this.titulo = datosLibro.titulo().substring(0, 255);
                System.out.println("El título es muy largo, se recorto a 255 caract");
        }



        this.idioma = datosLibro.idioma();

            try {
                this.numeroDeDescargas = Integer.valueOf(datosLibro.numeroDeDescargas());
            } catch (NumberFormatException e) {
                this.numeroDeDescargas = 0;
            }

        this.autor= datosAutor.nombre();

        if (datosAutor.nacimiento() != null) {
            try {
                this.nacimiento = Integer.valueOf(datosAutor.nacimiento());
            } catch (NumberFormatException e) {
                this.nacimiento =Integer.valueOf( 0);
            }
        } else {
            this.nacimiento = Integer.valueOf( 0);
        }


        this.muerte = datosAutor.muerte() != null ?
                Integer.valueOf(datosAutor.muerte()) : Integer.valueOf(0);

    }

    public Long getIdDB() {
        return IdDB;
    }

    public void setIdDB(Long idDB) {
        IdDB = idDB;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getIdioma() {
        return idioma;
    }

    public void setIdioma(List<String> idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        autor = autor;
    }

    public Integer getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Integer nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Integer getMuerte() {
        return muerte;
    }

    public void setMuerte(Integer muerte) {
        this.muerte = muerte;
    }
}



