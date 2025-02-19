package org.example.servicios;

import jakarta.persistence.NoResultException;
import java.util.List;
import java.util.Scanner;
import org.example.entidades.Autor;
import org.example.persistencias.AutorDAO;

public class AutorServicio {
  private final AutorDAO autorDAO;

  public AutorServicio() {
    this.autorDAO = new AutorDAO();
  }

  public void guardarAutor(String nombre){
    if (nombre == null || nombre.trim().isEmpty()) {
      throw new IllegalArgumentException("El nombre del autor es obligatorio.");
    }
    try{
      Autor autor = new Autor();
      autor.setNombre(nombre);
      autor.setAlta(true);
      autorDAO.guardarAutor(autor);
      System.out.println("Autor guardado exitosamente.");
    } catch(Exception e){
      System.out.println("Error: " + e.getMessage());
    }
  }

  public Autor buscarAutorPorId(Integer id){
    return autorDAO.buscarAutor(id);
  }

  public void buscarAutorPorNombre(String nombre){
    if (nombre == null || nombre.trim().isEmpty()) {
      throw new IllegalArgumentException("El nombre del autor es obligatorio.");
    }
    try {
      Autor autor = autorDAO.buscarAutorPorNombre(nombre);
      System.out.println("ID: " + autor.getIdAutor() + ", Nombre: " + autor.getNombre() + ", Alta: " + autor.isAlta());
    } catch (NoResultException e){
      System.out.println("Error: No se encontro el autor con nombre " + nombre + ".");
    } catch (Exception e) {
      System.out.println("Error al buscar el autor: " + e.getMessage());
    }
  }

  public void actualizarAutor(Integer id){
    try {
      Autor autor = buscarAutorPorId(id);
      if(autor != null){
        System.out.println("Ingrese nuevo nombre para el autor " + autor.getNombre());
        String nuevoNombre = new Scanner(System.in).nextLine();
        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
          throw new IllegalArgumentException("El nuevo nombre del autor es obligatorio.");
        }
        autor.setNombre(nuevoNombre);
        autorDAO.actualizarAutor(autor);
        System.out.println("Autor dado de baja exitosamente.");
      } else {
        System.out.println("No se encontro el autor.");
      }
    } catch (Exception e){
      System.out.println("Error: " + e.getMessage());
    }
  }

  public void darBajaAutor(Integer id){
    if (id == null) {
      throw new IllegalArgumentException("El ID del autor es obligatorio.");
    }
    try {
      autorDAO.eliminarAutor(id);
      System.out.println("Autor dado de baja exitosamente.");
    } catch (Exception e) {
      System.out.println("No se pudo dar de baja el autor: " + e.getMessage());
    }
  }

  public void reactivarAutor(Integer id) {
    if (id == null) {
      throw new IllegalArgumentException("El ID del autor es obligatorio.");
    }
    try {
      autorDAO.reactivarAutor(id);
      System.out.println("Autor reactivado exitosamente.");
    } catch (Exception e) {
      System.out.println("No se pudo reactivar el autor: " + e.getMessage());
    }
  }

  public void imprimirLista(List<Autor> lista) {
    if (lista.isEmpty()) {
      System.out.println("No hay autores para mostrar.");
    } else {
      for (Autor autor : lista) {
        System.out.println("ID: " + autor.getIdAutor() + ", Nombre: " + autor.getNombre() + ", Alta: " + autor.isAlta());
      }
    }
  }

  public void listarAutores(){
    List<Autor> autores = autorDAO.listarAutores();
    imprimirLista(autores);
  }

  public void listarTodosLosAutores() {
    List<Autor> autores = autorDAO.listarTodosLosAutores();
    imprimirLista(autores);
  }
}
