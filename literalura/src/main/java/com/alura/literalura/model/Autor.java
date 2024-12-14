package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "autor",unique = true, nullable = false)
    private String autor;

    private Integer nacimiento;
    private Integer muerte;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libro;

public Autor(){}

    public Autor(DatosAutor datosAutor){
        this.autor = datosAutor.nombre();

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

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
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

    public List<Libro> getLibro() {
        return libro;
    }

    public void setLibros(List<Libro> libro) {
        this.libro = libro;
    }
}