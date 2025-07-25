package com.challenger.literalura.services;

import com.challenger.literalura.model.Autor;
import com.challenger.literalura.model.DatosLibro;
import com.challenger.literalura.model.DatosResultado;
import com.challenger.literalura.model.Libro;
import com.challenger.literalura.repository.IAutorRepository;
import com.challenger.literalura.repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class LibrosService {
    @Autowired
    private ILibroRepository repository;
    @Autowired
    private IAutorRepository autorRepository;
    private Scanner teclado = new Scanner(System.in);
    private ConexionAPI conexionApi = new ConexionAPI();
    private ConversorJSON conversor = new ConversorJSON();
    private final String URL = "https://gutendex.com/books/";
    private String json;

    //devuelve un objeto Optional de DatosLibros de la api Gutendex
    private Optional<DatosLibro> buscarLibroPorNombreAPI(String nombreLibroBuscado){
        json = conexionApi.conexion(URL+"?search="+nombreLibroBuscado.replace(" ", "+"));

        var libroBuscado = conversor.obtenerDatos(json, DatosResultado.class);
        Optional<DatosLibro> first = libroBuscado.resultado().stream()
                //.filter(e->e.idiomas().contains("es")||e.idiomas().contains("en"))
                .filter(e -> e.titulo().toUpperCase().contains(nombreLibroBuscado.toUpperCase()))
                .findFirst();

        return first;
    }

    //insertar un libro a la base de datos y lo muestra en consola
    private void insertarLibro(Libro libro){
        if (repository.existsByTitulo(libro.getTitulo())){
            System.out.println("No se puede insertar, el libro ya existe!");
        }else {
            repository.save(libro);
            System.out.println("Libro encontrado: " + libro);
        }
    }

    //Hace uso de los dos metodos anteriores para la bsuqueda del libro por nombre
    public void buscarLibroPorNombre(){
        System.out.println("\nIngresa el nombre del libro que deseas buscar: ");
        String nombreLibroBuscado = teclado.nextLine();

        Optional<DatosLibro> datosLibro = buscarLibroPorNombreAPI(nombreLibroBuscado);

        if(datosLibro.isPresent()){
            Libro libro = new Libro(datosLibro.get());
            insertarLibro(libro);
        }else {
            System.out.println("No se encontro un libro con ese nombre en la API");
        }
    }

    //imprime la lista de libros que hay en la base de datos
    public void listarLibrosRegistrados() {
        List<Libro> libros = repository.findAll();
        libros.forEach(System.out::println);
    }

    //lista de autores
    //Pendiante de mejora, los autores no deben repetirse
    public void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(System.out::println);
    }

    public void listarAutoresPorAnio() {
        System.out.println("Ingrese el año vivo del autor(es) que desea buscar: ");
        int anio = teclado.nextInt();
        List<Autor> autors = autorRepository.findByAnioMuerteLessThan(anio);
        autors.forEach(System.out::println);
    }

    public void listarLibrosPorIdioma() {
        var menuIdioma = """
                    Ingrese el idioma para bucar los libros:
                    es - español
                    en - ingles
                    fr - frances
                    pt - portugues  
                    """;
        System.out.println(menuIdioma);
        String idioma = teclado.nextLine();
        List<Libro> libros = repository.buscarLibroPorIdioma(idioma);
        if(libros.isEmpty()){
            System.out.println("No se encontro un libro con ese idioma");
        }else{
            libros.forEach(System.out::println);
        }
    }
}
