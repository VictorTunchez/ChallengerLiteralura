package com.challenger.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Double anioNacimiento;

    private Double anioMuerte;

    private String nombre;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libro;

    public Autor(){}

    public Autor(DatosAutor datosAutor) {
        this.anioNacimiento = Double.valueOf(datosAutor.anioNacimiento());
        this.anioMuerte = Double.valueOf(datosAutor.anioMuerte());
        this.nombre = datosAutor.nombre();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(Double anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public Double getAnioMuerte() {
        return anioMuerte;
    }

    public void setAnioMuerte(Double anioMuerte) {
        this.anioMuerte = anioMuerte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }




}
