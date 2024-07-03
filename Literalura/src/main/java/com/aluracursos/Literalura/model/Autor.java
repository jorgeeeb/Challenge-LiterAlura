package com.aluracursos.Literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @JsonAlias("name")
    private String nombre;

    @JsonAlias("birth_year")
    private int añoNacimiento;

    @JsonAlias("death_year")
    private int añoFallecimiento;

    @ManyToMany(mappedBy = "autores", fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAñoNacimiento() {
        return añoNacimiento;
    }

    public void setAñoNacimiento(int añoNacimiento) {
        this.añoNacimiento = añoNacimiento;
    }

    public int getAñoFallecimiento() {
        return añoFallecimiento;
    }

    public void setAñoFallecimiento(int añoFallecimiento) {
        this.añoFallecimiento = añoFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "\nNombre: " + nombre +
                "\nAño de nacimiento: " + añoNacimiento +
                "\nAño de fallecimiento: " + añoFallecimiento +
                "\nLibros: " + libros.stream().map(Libro::getTitulo).toList() + "\n";
    }
}


