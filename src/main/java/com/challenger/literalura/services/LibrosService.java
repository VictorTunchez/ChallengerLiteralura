package com.challenger.literalura.services;

import com.challenger.literalura.model.DatosLibro;
import com.challenger.literalura.model.DatosResultado;
import com.challenger.literalura.model.Libro;
import com.challenger.literalura.repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class LibrosService {
    @Autowired
    private ILibroRepository repository;
    private Scanner teclado = new Scanner(System.in);
    private ConexionAPI conexionApi = new ConexionAPI();
    private ConversorJSON conversor = new ConversorJSON();
    private final String URL = "https://gutendex.com/books/";
    private String json;

    public void buscarLibroPorNombre(){
        System.out.println("\nIngresa el nombre del libro que deseas buscar: ");
        String nombreLibroBuscado = teclado.nextLine();
        json = conexionApi.conexion(URL+"?search="+nombreLibroBuscado.replace(" ", "+"));

        var libroBuscado = conversor.obtenerDatos(json, DatosResultado.class);
        Optional<DatosLibro> first = libroBuscado.resultado().stream()
                //.filter(e->e.idiomas().contains("es")||e.idiomas().contains("en"))
                .filter(e -> e.titulo().toUpperCase().contains(nombreLibroBuscado.toUpperCase()))
                .findFirst();

        if (first.isPresent()) {
            Libro libroEncontrado = new Libro(first.get());
            repository.save(libroEncontrado);
            System.out.println("Libro encontrado: " + libroEncontrado);
        } else {
            System.out.println("No se encontró un libro con ese nombre");
        }
    }
}
