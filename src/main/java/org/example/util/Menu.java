package org.example.util;

import java.util.Scanner;
import org.example.servicios.AutorServicio;
import org.example.servicios.EditorialServicio;
import org.example.servicios.LibroServicio;

public class Menu {
  public static void menuAutores(AutorServicio autorServicio, Scanner scanner) {
    while (true) {
      System.out.println("\n--- Gestión de Autores ---");
      System.out.println("1. Crear Autor");
      System.out.println("2. Listar Autores");
      System.out.println("3. Buscar Autor por Nombre");
      System.out.println("4. Actualizar Autor");
      System.out.println("5. Dar de Baja Autor");
      System.out.println("6. Reactivar Autor");
      System.out.println("7. Listar todas los Autores (Activos e Inactivos)");
      System.out.println("0. Volver al Menú Principal");
      System.out.print("Seleccione una opción: ");

      int opcion = scanner.nextInt();
      scanner.nextLine();

      switch (opcion) {
        case 1 -> {
          System.out.print("Ingrese nombre del autor: ");
          autorServicio.guardarAutor(scanner.nextLine());
        }
        case 2 -> autorServicio.listarAutores();
        case 3 -> {
          System.out.print("Ingrese nombre del autor a buscar: ");
          autorServicio.buscarAutorPorNombre(scanner.nextLine());
        }
        case 4 -> {
          System.out.print("Ingrese ID del autor: ");
          Integer id = scanner.nextInt();
          autorServicio.actualizarAutor(id);
        }
        case 5 -> {
          System.out.print("Ingrese ID del autor a dar de baja: ");
          autorServicio.darBajaAutor(scanner.nextInt());
        }
        case 6 -> {
          System.out.print("Ingrese ID del autor a reactivar: ");
          autorServicio.reactivarAutor(scanner.nextInt());
        }
        case 7 -> autorServicio.listarTodosLosAutores();
        case 0 -> {
          return;
        }
        default -> System.out.println("Opción inválida.");
      }
    }
  }

  public static void menuEditoriales(EditorialServicio editorialServicio, Scanner scanner) {
    while (true) {
      System.out.println("\n--- Gestión de Editoriales ---");
      System.out.println("1. Crear Editorial");
      System.out.println("2. Listar Editoriales");
      System.out.println("3. Buscar Editorial por Nombre");
      System.out.println("4. Actualizar Editorial");
      System.out.println("5. Dar de Baja Editorial");
      System.out.println("6. Reactivar Editorial");
      System.out.println("7. Listar todas las Editoriales (Activas e Inactivas)");
      System.out.println("0. Volver al Menú Principal");
      System.out.print("Seleccione una opción: ");

      int opcion = scanner.nextInt();
      scanner.nextLine();

      switch (opcion) {
        case 1 -> {
          System.out.print("Ingrese nombre de la editorial: ");
          editorialServicio.guardarEditorial(scanner.nextLine());
        }
        case 2 -> editorialServicio.listarEditoriales();
        case 3 -> {
          System.out.print("Ingrese nombre de la editorial a buscar: ");
          editorialServicio.buscarEditorialPorNombre(scanner.nextLine());
        }
        case 4 -> {
          System.out.print("Ingrese ID de la editorial: ");
          Integer id = scanner.nextInt();
          editorialServicio.actualizarEditorial(id);
        }
        case 5 -> {
          System.out.print("Ingrese ID de la editorial a dar de baja: ");
          editorialServicio.darBajaEditorial(scanner.nextInt());
        }
        case 6 -> {
          System.out.print("Ingrese ID de la editorial a reactivar: ");
          editorialServicio.reactivarEditorial(scanner.nextInt());
        }
        case 7 -> editorialServicio.listarTodasLasEditoriales();
        case 0 -> {
          return;
        }
        default -> System.out.println("Opción inválida.");
      }
    }
  }

  public static void menuLibros(LibroServicio libroServicio, Scanner scanner) {
    while (true) {
      System.out.println("\n--- Gestión de Libros ---");
      System.out.println("1. Crear Libro");
      System.out.println("2. Listar Libros");
      System.out.println("3. Buscar Libro por ISBN");
      System.out.println("4. Actualizar Libro");
      System.out.println("5. Actualizar ISBN de un libro");
      System.out.println("6. Agregar Ejemplares");
      System.out.println("7. Bajar Ejemplares");
      System.out.println("8. Dar de Baja Libro");
      System.out.println("9. Reactivar Libro");
      System.out.println("10. Listar todos los Libros (Activos e Inactivos)");
      System.out.println("11. Buscar Libros por Titulo");
      System.out.println("12. Buscar Libros por Autor");
      System.out.println("13. Buscar Libros por Editorial");
      System.out.println("0. Volver al Menú Principal");
      System.out.print("Seleccione una opción: ");

      int opcion = scanner.nextInt();
      scanner.nextLine();

      switch (opcion) {
        case 1 -> {
          System.out.println("Ingrese el ISBN del libro: ");
          Long isbn = scanner.nextLong();
          scanner.nextLine();
          System.out.println("Ingrese el título del libro: ");
          String titulo = scanner.nextLine();
          scanner.nextLine();
          System.out.println("Ingrese el año de publicación del libro: ");
          Integer anio = scanner.nextInt();
          System.out.println("Ingrese la cantidad de ejemplares: ");
          int ejemplares = scanner.nextInt();
          System.out.println("Ingrese el ID del autor: ");
          int idAutor = scanner.nextInt();
          System.out.print("Ingrese ID de la editorial: ");
          Integer idEditorial = scanner.nextInt();
          libroServicio.guardarLibro(isbn, titulo, anio, ejemplares, idAutor, idEditorial);
        }
        case 2 -> libroServicio.listarLibros();
        case 3 -> {
          System.out.println("Ingrese el ISBN del libro a buscar: ");
          libroServicio.buscarLibroPorISBN(scanner.nextLong());
        }
        case 4 -> {
          System.out.print("Ingrese ISBN del libro a actualizar: ");
          Long isbn = scanner.nextLong();
          scanner.nextLine();
          System.out.print("Ingrese nuevo título: ");
          String titulo = scanner.nextLine();
          System.out.print("Ingrese nuevo año de publicación: ");
          Integer anio = scanner.nextInt();
          System.out.print("Ingrese nuevo ID del autor: ");
          Integer idAutor = scanner.nextInt();
          System.out.print("Ingrese nuevo ID de la editorial: ");
          Integer idEditorial = scanner.nextInt();
          libroServicio.actualizarLibro(isbn, titulo, anio, idAutor, idEditorial);
        }
        case 5 -> {
          System.out.print("Ingrese ISBN del libro a actualizar: ");
          libroServicio.actualizarISBN(scanner.nextLong());
        }
        case 6 -> {
          System.out.print("Ingrese ISBN del libro: ");
          Long isbn = scanner.nextLong();
          System.out.print("Ingrese cantidad de ejemplares a agregar: ");
          libroServicio.agregarEjemplares(isbn, scanner.nextInt());
        }
        case 7 -> {
          System.out.println("Ingrese ISBN del libro: ");
          Long isbn = scanner.nextLong();
          System.out.println("Ingrese cantidad de ejemplares a bajar: ");
          libroServicio.bajarEjemplares(isbn, scanner.nextInt());
        }
        case 8 -> {
          System.out.print("Ingrese ISBN del libro a dar de baja: ");
          libroServicio.darBajaLibro(scanner.nextLong());
        }
        case 9 -> {
          System.out.print("Ingrese ISBN del libro a reactivar: ");
          libroServicio.reactivarLibro(scanner.nextLong());
        }
        case 10 -> libroServicio.listarTodosLosLibros();
        case 11 -> {
          System.out.println("Ingrese el titulo: ");
          libroServicio.buscarLibrosPorTitulo(scanner.nextLine());
        }
        case 12 -> {
          System.out.println("Ingrese el nombre del autor: ");
          libroServicio.buscarLibrosPorAutor(scanner.nextLine());
        }
        case 13 -> {
          System.out.println("Ingrese el nombre de la editorial: ");
          libroServicio.buscarLibrosPorEditorial(scanner.nextLine());
        }
        case 0 -> {
          return;
        }
        default -> System.out.println("Opción inválida.");
      }
    }
  }
}
