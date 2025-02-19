package org.example.persistencias;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Persistence;
import java.util.List;
import org.example.entidades.Autor;

public class AutorDAO {
  private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");
  private final EntityManager em = emf.createEntityManager();

  public void guardarAutor(Autor autor) {
    if (!existeAutorPorNombre(autor.getNombre())) {
      em.getTransaction().begin();
      em.persist(autor);
      em.getTransaction().commit();
    } else {
      throw new IllegalArgumentException("El autor con el nombre '" + autor.getNombre() + "' ya existe.");
    }
  }

  // Verifica si un autor ya existe por su nombre
  public boolean existeAutorPorNombre(String nombre) {
    Long count = em.createQuery("SELECT COUNT(a) FROM Autor a WHERE a.nombre = :nombre", Long.class)
        .setParameter("nombre", nombre)
        .getSingleResult();
    return count > 0;
  }

  public void actualizarAutor(Autor autor){
    em.getTransaction().begin();
    em.merge(autor);
    em.getTransaction().commit();
  }
  public Autor buscarAutor(Integer idAutor) {
    Autor autor = em.find(Autor.class, idAutor);
    if (autor == null) {
      throw new EntityNotFoundException("Autor con ID " + idAutor + " no encontrado.");
    }
    return autor;
  }

  public Autor buscarAutorPorNombre(String nombre){
    return em.createQuery("SELECT a FROM Autor a WHERE a.nombre = :nombre", Autor.class)
        .setParameter("nombre", nombre)
        .getSingleResult();
  }


  public void eliminarAutor(Integer idAutor){
    Autor autor = em.find(Autor.class, idAutor);
    if (autor != null) {
      em.getTransaction().begin();
      autor.setAlta(false);
      em.merge(autor);
      em.getTransaction().commit();
    } else {
      throw new EntityNotFoundException("Autor con ID " + idAutor + " no encontrado.");
    }
  }

  public void reactivarAutor(Integer idAutor) {
    Autor autor = em.find(Autor.class, idAutor);
    if (autor != null) {
      em.getTransaction().begin();
      autor.setAlta(true);
      em.merge(autor);
      em.getTransaction().commit();
    } else {
      throw new EntityNotFoundException("Autor con ID " + idAutor + " no encontrado.");
    }
  }

  public List<Autor> listarAutores() {
    return em.createQuery("SELECT a FROM Autor a WHERE a.alta = true", Autor.class).getResultList();
  }

  // Lista todos los autores, incluyendo los dados de baja
  public List<Autor> listarTodosLosAutores() {
    return em.createQuery("SELECT a FROM Autor a", Autor.class)
        .getResultList();
  }
}
