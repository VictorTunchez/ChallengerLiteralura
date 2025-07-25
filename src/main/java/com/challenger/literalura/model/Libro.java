package com.challenger.literalura.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String titulo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Autor autor;

    private List<String> idiomas;

    private Integer totalDescargas;

    public Libro(){}

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        if (datosLibro.autor() != null && !datosLibro.autor().isEmpty()) {
            DatosAutor datosAutor = datosLibro.autor().get(0);
            this.autor = new Autor(datosAutor); // Aquí guardamos todos sus datos
        }
        this.idiomas = datosLibro.idiomas();
        this.totalDescargas = datosLibro.totalDescargas();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getTotalDescargas() {
        return totalDescargas;
    }

    public void setTotalDescargas(Integer totalDescargas) {
        this.totalDescargas = totalDescargas;
    }

    @Override
    public String toString() {
        return "-----------Libro-----------\n" +
                "Título: " + titulo + "\n" +
                "Autor(es): " + autor.getNombre() + "\n" +
                "Idiomas: " + idiomas + "\n" +
                "Total de descargas: " + totalDescargas + "\n";
    }
}
