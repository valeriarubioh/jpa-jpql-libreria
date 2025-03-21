package org.example.servicios;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import java.util.List;
import java.util.Scanner;
import org.example.entidades.Autor;
import org.example.entidades.Editorial;
import org.example.entidades.Libro;
import org.example.persistencias.AutorDAO;
import org.example.persistencias.EditorialDAO;
import org.example.persistencias.LibroDAO;

public class LibroServicio {
  private final LibroDAO libroDAO;
  private final AutorDAO autorDAO;
  private final EditorialDAO editorialDAO;

  public LibroServicio() {
    this.autorDAO = new AutorDAO();
    this.editorialDAO = new EditorialDAO();
    this.libroDAO = new LibroDAO();
  }

  public void guardarLibro(Long isbn, String titulo,Integer anio, Integer ejemplares, Integer idAutor, Integer idEditorial) {
    if(isbn == null || titulo == null || titulo.trim().isEmpty() || anio == null || ejemplares == null || idAutor == null || idEditorial == null){
      throw new IllegalArgumentException("Todos los campos del libro, incluyendo autor y editorial, son obligatorios.");
    }
    try {
      Autor autor = autorDAO.buscarAutor(idAutor);
      Editorial editorial = editorialDAO.buscarEditorial(idEditorial);

      Libro libro = new Libro();
      libro.setIsbn(isbn);
      libro.setTitulo(titulo);
      libro.setAnio(anio);
      libro.setEjemplares(ejemplares);
      libro.setAutor(autor);
      libro.setEditorial(editorial);
      libro.setAlta(true);

      libroDAO.guardarLibro(libro);
      System.out.println("Libro guardado exitosamente.");
    } catch (EntityNotFoundException e){
      System.out.println("Error: " + e.getMessage());
    } catch (Exception e) {
      System.out.println("Error: no se pudo guardar el libro. " + e.getMessage());
    }
  }

  public void buscarLibroPorISBN(Long isbn) {
    if(isbn == null) throw new IllegalArgumentException("El isbn del libro es obligatorio");

    Libro libro = libroDAO.buscarLibroPorISBN(isbn);
    if (libro != null) {
      System.out.println("Título: " + libro.getTitulo());
    } else {
      System.out.println("No se encontró el libro con ISBN: " + isbn);
    }
  }

  public void actualizarLibro(Long isbn, String titulo, Integer anio, Integer idAutor, Integer idEditorial){
    if (isbn == null || titulo == null || titulo.trim().isEmpty() || anio == null || idAutor == null || idEditorial == null) {
      throw new IllegalArgumentException("Todos los campos del libro, incluyendo autor y editorial, son obligatorios.");
    }

    try {
      Libro libro = libroDAO.buscarLibroPorISBN(isbn);
      boolean actualizado = false;

      if(libro!= null){

        if (!libro.getTitulo().equals(titulo)) {
          libro.setTitulo(titulo);
          actualizado = true;
        }
        if (!libro.getAnio().equals(anio)) {
          libro.setAnio(anio);
          actualizado = true;
        }
        if (!libro.getAutor().getIdAutor().equals(idAutor)) {
          Autor autor = autorDAO.buscarAutor(idAutor);
          libro.setAutor(autor);
          actualizado = true;
        }
        if (!libro.getEditorial().getIdEditorial().equals(idEditorial)) {
          Editorial editorial = editorialDAO.buscarEditorial(idEditorial);
          libro.setEditorial(editorial);
          actualizado = true;
        }

        if (actualizado) {
          libroDAO.actualizarLibro(libro);
          System.out.println("Libro actualizado exitosamente.");
        } else {
          System.out.println("No se realizaron cambios ya que los datos proporcionados son idénticos a los actuales.");
        }
      } else {
        System.out.println("No se encontró el libro con ISBN: " + isbn);
      }
    } catch(Exception e) {
      System.out.println("Error: no se pudo actualizar el libro con ISBN: " + isbn);
    }
  }

  public void agregarEjemplares(Long isbn, Integer nuevosEjemplares) {
    if(isbn == null || nuevosEjemplares == null || nuevosEjemplares == 0){
      throw new IllegalArgumentException("El isbn del libro o la cantidad de ejemplares no son validos");
    }
    try {
      Libro libro = libroDAO.buscarLibroPorISBN(isbn);
      if (libro != null) {
        libro.setEjemplares(libro.getEjemplares() + nuevosEjemplares);
        libroDAO.actualizarLibro(libro);
        System.out.println("Ejemplares actualizados exitosamente del libro con ISBN " + isbn);
      } else {
        System.out.println("No se encontró el libro con ISBN: " + isbn);
      }
    } catch (Exception e) {
      System.out.println("Error al actualizar ejemplares: " + e.getMessage());
    }
  }

  public void bajarEjemplares(Long isbn, Integer ejemplares) {
    if(isbn == null || ejemplares == null || ejemplares == 0){
      throw new IllegalArgumentException("El isbn del libro o la cantidad de ejemplares no son validos");
    }
    try {
      Libro libro = libroDAO.buscarLibroPorISBN(isbn);
      if (libro != null) {
        if(libro.getEjemplares() >= ejemplares){
          libro.setEjemplares(libro.getEjemplares() - ejemplares);
          libroDAO.actualizarLibro(libro);
          System.out.println("Ejemplares actualizados exitosamente del libro con ISBN " + isbn);
        } else {
          System.out.println("No puedes bajar " + ejemplares + " ejemplares del libro con ISBN " + isbn + " pues no hay suficientes");
        }

      } else {
        System.out.println("No se encontró el libro con ISBN: " + isbn);
      }
    } catch (Exception e) {
      System.out.println("Error al actualizar ejemplares: " + e.getMessage());
    }
  }

  public void actualizarISBN(Long isbn){
    if(isbn == null){
      throw new IllegalArgumentException("El isbn del libro es obligatorio");
    }
    try {
      Libro libro = libroDAO.buscarLibroPorISBN(isbn);
      if(libro!= null){
        System.out.println("Ingrese nuevo ISBN para el libro " + libro.getTitulo());
        Long nuevoISBN = new Scanner(System.in).nextLong();
        libro.setIsbn(nuevoISBN);
        libroDAO.actualizarLibro(libro);
        System.out.println("ISBN actualizado exitosamente");
      } else {
        System.out.println("No se encontró el libro con ISBN: " + isbn);
      }
    } catch (Exception e) {
      System.out.println("Error: no se pudo actualizar el ISBN " + e.getMessage());
    }
  }

  public void darBajaLibro(Long isbn) {
    if(isbn == null){
      throw new IllegalArgumentException("El isbn del libro es obligatorio");
    }
    try {
      libroDAO.darBajaLibro(isbn);
      System.out.println("Libro dado de baja exitosamente");
    } catch (Exception e){
      System.out.println("Error: no se pudo dar de baja el libro" + e.getMessage());
    }
  }

  public void reactivarLibro(Long isbn) {
    if(isbn == null){
      throw new IllegalArgumentException("El isbn del libro es obligatorio");
    }
    try {
      libroDAO.reactivarLibro(isbn);
      System.out.println("Libro reactivado exitosamente");
    } catch (Exception e){
      System.out.println("Error: no se pudo reactivar el libro" + e.getMessage());
    }
  }

  public void buscarLibrosPorTitulo(String titulo) {
    if (titulo == null || titulo.trim().isEmpty()) {
      throw new IllegalArgumentException("El título del libro es obligatorio para la búsqueda.");
    }
    try {
      List<Libro> libros = libroDAO.buscarLibrosPorTitulo(titulo.trim());
      imprimirLista(libros);
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }

  }

  public void buscarLibrosPorAutor(String nombreAutor) {
    if (nombreAutor == null || nombreAutor.trim().isEmpty()) {
      throw new IllegalArgumentException("El nombre del autor es obligatorio para la búsqueda.");
    }
    try {
      List<Autor> autores = autorDAO.buscarAutorPorNombre(nombreAutor);
      if(autores.isEmpty()){
        System.out.println("No se encontraron autores que coincidan con: " + nombreAutor);
        return;
      }
      for (Autor autor : autores) {
        System.out.println("Autor encontrado: " + autor.getNombre() + " (ID: " + autor.getIdAutor() + ")");
        List<Libro> libros = libroDAO.buscarLibrosPorAutorId(autor.getIdAutor());
        imprimirLista(libros);
      }
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public void buscarLibrosPorEditorial(String nombreEditorial) {
    if (nombreEditorial == null || nombreEditorial.trim().isEmpty()) {
      throw new IllegalArgumentException(
          "El nombre de la editorial es obligatorio para la búsqueda.");
    }
    try {
      List<Editorial> editoriales = editorialDAO.buscarEditorialesPorNombre(nombreEditorial);
      if (editoriales.isEmpty()) {
        System.out.println("No se encontraron editoriales que coincidan con: " + nombreEditorial);
        return;
      }
      for (Editorial editorial : editoriales) {
        System.out.println("Editorial encontrada: " + editorial.getNombre() + " (ID: " + editorial.getIdEditorial() + ")");
        List<Libro> libros = libroDAO.buscarLibrosPorEditorialId(editorial.getIdEditorial());
        imprimirLista(libros);
      }
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public void imprimirLista(List<Libro> lista) {
    if (lista.isEmpty()) {
      System.out.println("No hay libros para mostrar.");
    } else {
      for (Libro libro : lista) {
        String editorialNombre = (libro.getEditorial() != null) ? libro.getEditorial().getNombre() : "null";
        System.out.println("ISBN: " + libro.getIsbn() + ", Titulo: " + libro.getTitulo() +
            ", Año: " + libro.getAnio() + ", Ejemplares: " + libro.getEjemplares() +
            ", Editorial: " + editorialNombre + ", Autor: " + libro.getAutor().getNombre() +
            ", Alta: " + libro.isAlta());
      }
    }
  }

  public void listarLibros(){
    List<Libro> libros = libroDAO.listarLibros();
    imprimirLista(libros);
  }

  public void listarTodosLosLibros() {
    List<Libro> libros = libroDAO.listarTodosLosLibros();
    imprimirLista(libros);
  }
}
