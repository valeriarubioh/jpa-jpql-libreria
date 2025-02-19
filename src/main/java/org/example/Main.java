package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Scanner;
import org.example.servicios.AutorServicio;
import org.example.servicios.EditorialServicio;
import org.example.servicios.LibroServicio;
import org.example.util.Menu;

public class Main {

  public static void main(String[] args) {
//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");
//    EntityManager em = emf.createEntityManager();

    Scanner scanner = new Scanner(System.in);
    AutorServicio autorServicio = new AutorServicio();
    EditorialServicio editorialServicio = new EditorialServicio();
    LibroServicio libroServicio = new LibroServicio();

    while (true) {
      System.out.println("\n\n--- Menú Librería ---");
      System.out.println("1. Gestionar Autores");
      System.out.println("2. Gestionar Editoriales");
      System.out.println("3. Gestionar Libros");
      System.out.println("0. Salir");
      System.out.print("Seleccione una opción: ");

      int opcion = scanner.nextInt();
      scanner.nextLine();

      switch (opcion) {
        case 1 -> Menu.menuAutores(autorServicio, scanner);
        case 2 -> Menu.menuEditoriales(editorialServicio, scanner);
        case 3 -> Menu.menuLibros(libroServicio, scanner);
        case 0 -> {
          System.out.println("Saliendo...");
          return;
        }
        default -> System.out.println("Opción inválida.");
      }
    }
  }

}