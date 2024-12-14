package com.alura.literalura.principal;


import com.alura.literalura.model.*;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;
import com.alura.literalura.service.ProcesarJson;


import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private String dbURL = System.getenv("DB_URL");
    private ProcesarJson procesarJson = new ProcesarJson();
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversorDatosGenerales = new ConvierteDatos();
    private AutorRepository repositorio;
    //private BibliotecaRepository repositorioBiblioteca;

    public Principal(AutorRepository repository) {
        this.repositorio= repository;
    }

    /*public Principal(BibliotecaRepository repositoryBiblioteca) {
        this.repositorioBiblioteca= repositoryBiblioteca;
    }*/


    public void muestraElMenu () {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
               
               **************************************
               
               Bienvenido a LiterAlura, ¿Que quieres hacer hoy? 
               
                1 - Buscar Libro por Titulo
                2 - Mostrar Libros Registrados
                3 - Mostrar Autores Registrados
                4 - Mostrar Autores Vivos en un Año Seleccionado
                5 - Mostrar Libros por Idioma
            
                0 - Salir del programa
                **************************************
                
                Elige el número de la acción a realizar:
                """;
            System.out.println(menu);
            try {
                opcion = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingresa un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    getDatosLibro();
                    break;
                case 2:
                    getLibrosBuscados();
                    break;
                case 3:
                    getAutoresBuscados();
                    break;
                case 4:
                    getAutoresVivosEnUnPeriodo();
                    break;
                case 5:
                    getBusquedaPorIdioma();
                    break;

                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private  DatosLibro getDatosLibro() {
        System.out.println("Escribe el nombre del libro que deseas guardar en la Base de Datos:");
        var nombreLibro = teclado.nextLine().toUpperCase();
        var json = consumoAPI.obtenerDatos(dbURL+ "/?search=" + nombreLibro.replace(" ", "%20"));
        String primerResultadoGeneral = procesarJson.procesarJsonGeneral(json);
        String primerResultadoAutor = procesarJson.procesarJsonAutor(json);

        if (primerResultadoGeneral != null) {
            DatosLibro datosLibro = conversorDatosGenerales.obtenerDatos(primerResultadoGeneral, DatosLibro.class);
            DatosAutor datosAutor = conversorDatosGenerales.obtenerDatos(primerResultadoAutor, DatosAutor.class);

            System.out.println("******* LIBRO *********\n" +

                    "Titulo del Libro: " + datosLibro.titulo() + "\n" +
                    "Autor del Libro: " + datosAutor.nombre() + "\n" +
                    "Idioma del Libro: " + datosLibro.idioma().stream().collect(Collectors.joining(", ")) + "\n" +
                    "Descargas del Libro: " + datosLibro.numeroDeDescargas() + "\n" + "\n" +
                    "Quieres agregar este libro a la base de datos? (Si/No)\n" +
                    "****************");

            var agregar = teclado.nextLine().toUpperCase();

            if (agregar.equals("SI")){

               //Biblioteca libro = new Biblioteca( datosLibro, datosAutor);
               //repositorioBiblioteca.save(libro);

                Optional<Autor> autorValidacion = repositorio.findByAutor(datosAutor.nombre());
                if (autorValidacion.isPresent()) {
                    Autor autor = autorValidacion.get();


                    boolean libroExiste = autor.getLibro().stream()
                            .anyMatch(libro -> libro.getTitulo().equalsIgnoreCase(datosLibro.titulo()));

                    if (libroExiste) {
                        System.out.println("\nEl libro ya esta registrado");
                    } else {

                        Libro libro = new Libro(datosLibro);
                        libro.setAutor(autor);
                        autor.getLibro().add(libro);
                        repositorio.save(autor);

                        System.out.println("\nLibro Guardado con Exito");
                    }
                } else {

                        Autor nuevoAutor = new Autor(datosAutor);
                        Libro nuevoLibro = new Libro(datosLibro);
                        nuevoLibro.setAutor(nuevoAutor);
                        nuevoAutor.setLibros(List.of(nuevoLibro));
                        repositorio.save(nuevoAutor);

                        System.out.println("\nLibro Guardado con Exito");
                    }

            }else {
                System.out.println("\nVuelve a empezar");
            }
        }
        return null;
    }

    private void getLibrosBuscados() {

        List<Autor> autores = repositorio.findAll();

        autores.stream()
                .forEach(autor -> autor.getLibro().forEach(libro ->
                        System.out.println(
                                "\n******* LIBRO *********" +"\n"+
                                "Titulo del Libro: " + libro.getTitulo()+"\n"+
                                "Autor del Libro: " + autor.getAutor()+"\n"+
                                "Idioma del Libro: " + libro.getIdioma()+"\n"+
                                "Descargas del Libro: " + libro.getNumeroDeDescargas()+"\n"+
                                "****************")));


    }



    private void getAutoresBuscados() {

        List<Autor> autores = repositorio.findAll();

        autores.stream()
                .forEach(autor ->
                                System.out.println(
                        "\n******* AUTOR *********" +"\n"+
                                "Autor: " + autor.getAutor()+"\n"+
                                "Fecha de nacimiento: " + autor.getNacimiento()+"\n"+
                                "Fecha de muerte: " + autor.getMuerte()+"\n"+
                                "Libros: " + autor.getLibro().stream().map(libro -> libro.getTitulo()).collect(Collectors.toList())  +"\n"+
                                "****************"));

    }

    private void getAutoresVivosEnUnPeriodo(){

        System.out.println("¿Que Año deseas que se filtren los autores?");
        var evaluacion = teclado.nextInt();
        teclado.nextLine();

        List<Autor> filtroAutores = repositorio.autoresVivosEnUnPeriodo(evaluacion);
        System.out.println("\nAutores Filtrados\n");
        filtroAutores.forEach(autor ->
                System.out.println(
                        "******* AUTOR *********" +"\n"+
                                "Autor: " + autor.getAutor()+"\n"+
                                "Fecha de nacimiento: " + autor.getNacimiento()+"\n"+
                                "Fecha de muerte: " + autor.getMuerte()+"\n"+
                                "Libros: " + autor.getLibro().stream().map(libro -> libro.getTitulo()).collect(Collectors.toList())  +"\n"+
                                "****************\n"));

    }

    private void getBusquedaPorIdioma() {
        System.out.println("\nIngresa el idioma para buscar el libro:\n" +
                "en - Inglés\n" +
                "es - Español\n" +
                "fr - Francés\n" +
                "pt - Portugués"
        );
        var idioma = teclado.nextLine();
        List<Autor> filtroIdiomas = repositorio.busquedaPorIdioma(idioma);

        System.out.println("\nIdiomas Filtrados\n");

        filtroIdiomas.stream()
                .forEach(autor -> autor.getLibro().forEach(libro ->
                        System.out.println(
                                "\n******* LIBRO *********" +"\n"+
                                        "Titulo del Libro: " + libro.getTitulo()+"\n"+
                                        "Autor del Libro: " + autor.getAutor()+"\n"+
                                        "Idioma del Libro: " + libro.getIdioma()+"\n"+
                                        "Descargas del Libro: " + libro.getNumeroDeDescargas()+"\n"+
                                        "****************")));



    }



}
