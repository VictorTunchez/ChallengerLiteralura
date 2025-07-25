package com.challenger.literalura.principal;

import com.challenger.literalura.services.LibrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.InputMismatchException;
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
            String entrada = teclado.nextLine();
            try {
                opcion = Integer.parseInt(entrada);
            }catch (NumberFormatException e){
                System.out.println("No se reconocio la entrada, ingresa un opcion valida");
                continue;
            }
            switch (opcion) {
                case 1:
                    servicio.buscarLibroPorNombre();
                    break;
                case 2:
                    servicio.listarLibrosRegistrados();
                    break;
                case 3:
                    servicio.listarAutoresRegistrados();
                    break;
                case 4:
                    servicio.listarAutoresPorAnio();
                    break;
                case 5:
                    servicio.listarLibrosPorIdioma();
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
