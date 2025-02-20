package org.example.servicios;

import jakarta.persistence.NoResultException;
import java.util.List;
import java.util.Scanner;
import org.example.entidades.Editorial;
import org.example.persistencias.EditorialDAO;

public class EditorialServicio {
  private final EditorialDAO editorialDAO;

  public EditorialServicio() {
    this.editorialDAO = new EditorialDAO();
  }

  public void guardarEditorial(String nombre) {
    if (nombre == null || nombre.trim().isEmpty()) {
      throw new IllegalArgumentException("El nombre de la editorial es obligatorio.");
    }
    try {
      Editorial editorial = new Editorial();
      editorial.setNombre(nombre);
      editorial.setAlta(true);
      editorialDAO.guardarEditorial(editorial);
      System.out.println("Editorial guardada exitosamente");
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public Editorial buscarEditorialPorId(Integer idEditorial) {
    return editorialDAO.buscarEditorial(idEditorial);
  }

  public void buscarEditorialPorNombre(String nombre) {
    if (nombre == null || nombre.trim().isEmpty()) {
      throw new IllegalArgumentException("El nombre de la editorial es obligatorio.");
    }
    try {
      Editorial editorial = editorialDAO.buscarEditorialPorNombre(nombre);
      System.out.println("ID: " + editorial.getIdEditorial() + ", Nombre: " + editorial.getNombre() + ", Alta: " + editorial.isAlta());
    } catch (NoResultException e){
      System.out.println("Error: No se encontró la editorial con nombre " + nombre + ".");
    } catch (Exception e) {
      System.out.println("Error al buscar la editorial: " + e.getMessage());
    }
  }

  public void actualizarEditorial(Integer id) {
    try {
      Editorial editorial = buscarEditorialPorId(id);
      if (editorial!= null) {
        System.out.print("Ingrese nuevo nombre: ");
        String nombre = new Scanner(System.in).nextLine();
        if (nombre == null || nombre.trim().isEmpty()) {
          throw new IllegalArgumentException("El nuevo nombre de la editorial es obligatorio.");
        }
        editorial.setNombre(nombre);
        editorialDAO.actualizarEditorial(editorial);
        System.out.println("Editorial actualizada exitosamente.");
      } else {
        System.out.println("No se encontro la editorial.");
      }
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public void darBajaEditorial(Integer id) {
    if (id == null) {
      throw new IllegalArgumentException("El ID de la editorial es obligatorio.");
    }
    try {
      editorialDAO.darBajaEditorial(id);
      System.out.println("Editorial dada de baja exitosamente.");
    } catch (Exception e) {
      System.out.println("No se pudo dar de baja la editorial: " + e.getMessage());
    }
  }

  public void reactivarEditorial(Integer id) {
    if (id == null) {
      throw new IllegalArgumentException("El ID de la editorial es obligatorio.");
    }
    try {
      editorialDAO.reactivarEditorial(id);
      System.out.println("Editorial reactivada exitosamente.");
    } catch (Exception e) {
      System.out.println("No se pudo reactivar la editorial: " + e.getMessage());
    }
  }

  public void imprimirLista(List<Editorial> lista) {
    if (lista.isEmpty()) {
      System.out.println("No hay editoriales para mostrar.");
    } else {
      for (Editorial editorial : lista) {
        System.out.println("ID: " + editorial.getIdEditorial() + ", Nombre: " + editorial.getNombre() + ", Alta: " + editorial.isAlta());
      }
    }
  }

  public void listarEditoriales() {
    List<Editorial> editoriales = editorialDAO.listarEditoriales();
    imprimirLista(editoriales);
  }

  public void listarTodasLasEditoriales() {
    List<Editorial> editoriales = editorialDAO.listarTodasLasEditoriales();
    imprimirLista(editoriales);
  }
}
