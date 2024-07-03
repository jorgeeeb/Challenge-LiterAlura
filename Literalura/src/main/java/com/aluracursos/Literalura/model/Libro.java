package com.aluracursos.Literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonAlias("title")
    @Column(unique = true)
    private String titulo;

    @JsonAlias("authors")
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores;

    @JsonAlias("languages")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> idiomas;

    @JsonAlias("download_count")
    private int descargas;

    public Libro() {}

    public Libro(String titulo, List<Autor> autores, List<String> idiomas) {
        this.titulo = titulo;
        this.autores = autores;
        this.idiomas = idiomas;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public int getDescargas() {
        return descargas;
    }

    public void setDescargas(int descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        String autoresNombres = autores.stream()
                .map(Autor::getNombre)
                .collect(Collectors.joining("; "));
        return "******LIBRO******" +
                "\nTítulo: " + titulo +
                "\nAutor(es): " + autoresNombres +
                "\nIdioma: " + idiomas +
                "\nCantidad de descargas:" + descargas +
                "\n****************\n";
    }
}
