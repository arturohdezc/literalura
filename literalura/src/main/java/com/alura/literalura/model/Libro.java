package com.alura.literalura.model;

import jakarta.persistence.*;




@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdDB;
    @Column(unique = true)
    private String titulo;
    private String id;
    private String idioma;
    private Integer numeroDeDescargas;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Libro(){}

    public Libro(DatosLibro datosLibro){
        this.id = datosLibro.id();


        if (datosLibro.titulo().length() > 255) {
            this.titulo = datosLibro.titulo().substring(0, 255);
            System.out.println("El título es muy largo, se recorto a 255 caract");
        } else {
            this.titulo = datosLibro.titulo();
        }



        this.idioma = datosLibro.idioma().get(0);

        try {
            this.numeroDeDescargas = Integer.valueOf(datosLibro.numeroDeDescargas());
        } catch (NumberFormatException e) {
            this.numeroDeDescargas = 0;
        }

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

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}



