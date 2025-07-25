package com.challenger.literalura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int anioNacimiento;

    private int anioMuerte;

    private String nombre;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libro;

    public Autor() {
    }

    public Autor(DatosAutor datosAutor) {
        if (datosAutor.anioNacimiento() != null) {
            this.anioNacimiento = Integer.parseInt(datosAutor.anioNacimiento());
        } else {
            this.anioNacimiento = 0;
        }
        if (datosAutor.anioMuerte() != null) {
            this.anioMuerte = Integer.parseInt(datosAutor.anioMuerte());
        } else {
            this.anioMuerte = 0;
        }
        this.nombre = datosAutor.nombre();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(int anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public int getAnioMuerte() {
        return anioMuerte;
    }

    public void setAnioMuerte(int anioMuerte) {
        this.anioMuerte = anioMuerte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Libro> getLibro() {
        return libro;
    }

    public void setLibro(List<Libro> libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return "Autor: " + nombre + "\n" +
                "Fecha de nacimiento: " + anioNacimiento + "\n" +
                "Fecha de fallecimiento: " + anioMuerte + "\n" +
                "Libros: [" + libro.stream()
                .map(Libro::getTitulo)
                .collect(Collectors.joining(", ")) +
                "]\n";
    }
}
