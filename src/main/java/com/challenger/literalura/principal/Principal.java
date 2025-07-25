package com.challenger.literalura.principal;

import com.challenger.literalura.model.Libro;
import com.challenger.literalura.repository.ILibroRepository;
import com.challenger.literalura.services.LibrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class Principal {
    @Autowired
    private LibrosService servicio;
    private Scanner teclado = new  Scanner(System.in);

    public Principal(LibrosService servicio) {
        this.servicio =servicio;
    }

    public void muestraElMenu()  {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    Elija la opcion atraves de su numero: 
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    servicio.buscarLibroPorNombre();
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

}
