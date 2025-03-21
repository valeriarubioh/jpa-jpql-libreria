package org.example.util;

import java.util.InputMismatchException;
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

      int opcion = validarEntero(scanner);
      String nombre;
      Integer id;

      switch (opcion) {
        case 1 -> {
          System.out.print("Ingrese nombre del autor: ");
          nombre = validarString(scanner);
          autorServicio.guardarAutor(nombre);
        }
        case 2 -> autorServicio.listarAutores();
        case 3 -> {
          System.out.print("Ingrese nombre del autor a buscar: ");
          nombre = validarString(scanner);
          autorServicio.buscarAutorPorNombre(nombre);
        }
        case 4 -> {
          System.out.print("Ingrese ID del autor: ");
          id = validarEntero(scanner);
          autorServicio.actualizarAutor(id);
        }
        case 5 -> {
          System.out.print("Ingrese ID del autor a dar de baja: ");
          id = validarEntero(scanner);
          autorServicio.darBajaAutor(id);
        }
        case 6 -> {
          System.out.print("Ingrese ID del autor a reactivar: ");
          id = validarEntero(scanner);
          autorServicio.reactivarAutor(id);
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

      int opcion = validarEntero(scanner);
      String nombre;
      Integer id;

      switch (opcion) {
        case 1 -> {
          System.out.print("Ingrese nombre de la editorial: ");
          nombre = validarString(scanner);
          editorialServicio.guardarEditorial(nombre);
        }
        case 2 -> editorialServicio.listarEditoriales();
        case 3 -> {
          System.out.print("Ingrese nombre de la editorial a buscar: ");
          nombre = validarString(scanner);
          editorialServicio.buscarEditorialPorNombre(nombre);
        }
        case 4 -> {
          System.out.print("Ingrese ID de la editorial: ");
          id = validarEntero(scanner);
          editorialServicio.actualizarEditorial(id);
        }
        case 5 -> {
          System.out.print("Ingrese ID de la editorial a dar de baja: ");
          id = validarEntero(scanner);
          editorialServicio.darBajaEditorial(id);
        }
        case 6 -> {
          System.out.print("Ingrese ID de la editorial a reactivar: ");
          id = validarEntero(scanner);
          editorialServicio.reactivarEditorial(id);
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

      int opcion = validarEntero(scanner);

      switch (opcion) {
        case 1 -> {
          System.out.println("Ingrese el ISBN del libro: ");
          Long isbn = validarLong(scanner);
          scanner.nextLine();
          System.out.println("Ingrese el título del libro: ");
          String titulo = validarString(scanner);
          scanner.nextLine();
          System.out.println("Ingrese el año de publicación del libro: ");
          Integer anio = validarEntero(scanner);
          System.out.println("Ingrese la cantidad de ejemplares: ");
          int ejemplares = validarEntero(scanner);
          System.out.println("Ingrese el ID del autor: ");
          int idAutor = validarEntero(scanner);
          System.out.print("Ingrese ID de la editorial: ");
          Integer idEditorial = validarEntero(scanner);
          libroServicio.guardarLibro(isbn, titulo, anio, ejemplares, idAutor, idEditorial);
        }
        case 2 -> libroServicio.listarLibros();
        case 3 -> {
          System.out.println("Ingrese el ISBN del libro a buscar: ");
          Long isbn = validarLong(scanner);
          libroServicio.buscarLibroPorISBN(isbn);
        }
        case 4 -> {
          System.out.print("Ingrese ISBN del libro a actualizar: ");
          Long isbn = validarLong(scanner);
          System.out.print("Ingrese nuevo título: ");
          String titulo = validarString(scanner);
          System.out.print("Ingrese nuevo año de publicación: ");
          Integer anio = validarEntero(scanner);
          System.out.print("Ingrese nuevo ID del autor: ");
          Integer idAutor = validarEntero(scanner);
          System.out.print("Ingrese nuevo ID de la editorial: ");
          Integer idEditorial = validarEntero(scanner);
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

  private static int validarEntero(Scanner scanner) {
    while (true) {
      try {
        int numero = scanner.nextInt();
        scanner.nextLine();
        return numero;
      } catch (InputMismatchException e) {
        System.out.println("Entrada inválida. Ingrese un número entero:");
        scanner.nextLine();
      }
    }
  }
  private static String validarString(Scanner scanner) {
    String entrada;
    do {
      entrada = scanner.nextLine().trim();
      if (entrada.isEmpty()) {
        System.out.println("No puede estar vacío. Intente de nuevo:");
      }
    } while (entrada.isEmpty());
    return entrada;
  }

  private static Long validarLong(Scanner scanner) {
    while (true) {
      try {
        return scanner.nextLong();
      } catch (InputMismatchException e) {
        System.out.println("Entrada inválida. Ingrese un número válido:");
        scanner.nextLine();
      }
    }
  }
}
