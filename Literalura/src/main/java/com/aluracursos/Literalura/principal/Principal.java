package com.aluracursos.Literalura.principal;

import com.aluracursos.Literalura.model.Autor;
import com.aluracursos.Literalura.model.DatosLibro;
import com.aluracursos.Literalura.model.Libro;
import com.aluracursos.Literalura.repository.AutorRepository;
import com.aluracursos.Literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.aluracursos.Literalura.service.ConsumoAPI;
import com.aluracursos.Literalura.service.ConvierteDatos;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Principal {
    private Scanner lectura = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    @Autowired
    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }



    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    ***LITERALURA***
                    
                    Elija la opción que desee.
                    
                    1 - Buscar libro por titulo. 
                    2 - Listar libros registrados.
                    3 - Listar autores registrados.
                    4 - Listar autores vivos en un determinado año.
                    5 - Listar libro por idioma.
                                  
                    0 - Salir.
                    
                    ***LITERALURA***
                    """;
            System.out.println(menu);
            opcion = lectura.nextInt();
            lectura.nextLine();

            switch (opcion) {
                case 1:
                    getDatosLibro();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }
    private DatosLibro getDatosLibro(){
        System.out.println("Escriba el nombre del libro que está buscando.");
        var nombreLibro = lectura.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "+"));
        DatosLibro datos = conversor.obtenerDatos(json, DatosLibro.class);
        Libro primerLibro = datos.getLibros().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontró ningún libro."));
        DatosLibro datosConUnSoloLibro = new DatosLibro();
        System.out.println(primerLibro);

        try {
            for (Autor autor : primerLibro.getAutores()){
                if (autor.getId() == null) {
                    autorRepository.save(autor);
                }
            }
            libroRepository.save(primerLibro);
            System.out.println(primerLibro);
        } catch (DataIntegrityViolationException e){
            System.out.println("El libro ya está en la base de datos: \n" + primerLibro);
        }
        return datosConUnSoloLibro;
    }


    private void listarLibrosRegistrados() {
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados en la base de datos.");
        } else {
            System.out.println("Libros registrados:");
            for (Libro libro : libros) {
                System.out.println(libro);
            }
        }
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAllWithLibros();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados en la base de datos.");
        } else {
            System.out.println("Autores registrados:");
            for (Autor autor : autores) {
                System.out.println(autor);
            }
        }
    }

    @Transactional
    private void listarAutoresVivos() {
        System.out.println("Escriba el año sobre el que desea saber los autores vivos");
        int año = lectura.nextInt();
        List<Autor> autoresVivos = autorRepository.findAutoresVivosEnAño(año);
        System.out.println("Autores vivos en el año " + año + ":");
        for (Autor autor : autoresVivos){
            System.out.println("\nNombre: " + autor.getNombre() +
                    "\nFecha de Nacimiento: " + autor.getAñoNacimiento() +
                    "\nFecha de Fallecimiento: " + autor.getAñoFallecimiento() + "\n");

        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("Ingrese el idioma para listar los libros:" +
                "\n[Es] = Español. \n[En] = Ingles. \n[Pt] = Portugués." );
        String idioma = lectura.nextLine();
        List<Libro> librosPorIdioma = libroRepository.findLibrosPorIdioma(idioma);
        System.out.println("Libros en el idioma seleccionado:\n");
        for (Libro libro : librosPorIdioma) {
            System.out.println(libro);
        }
    }
}
