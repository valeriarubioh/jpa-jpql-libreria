package org.example.persistencias;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Persistence;
import java.util.List;
import org.example.entidades.Libro;

public class LibroDAO {
  private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");
  private final EntityManager em = emf.createEntityManager();

  public void guardarLibro(Libro libro){
    if(!existeLibroPorISBN(libro.getIsbn())){
      em.getTransaction().begin();
      em.persist(libro);
      em.getTransaction().commit();
    } else {
      throw new IllegalArgumentException("El libro con ISBN '" + libro.getIsbn() + "' ya existe.");
    }
  }

  public boolean existeLibroPorISBN(Long isbn) {
    Long count = em.createQuery("SELECT COUNT(l) FROM Libro l WHERE l.isbn = :isbn", Long.class)
        .setParameter("isbn", isbn)
        .getSingleResult();
    return count > 0;
  }

  public Libro buscarLibroPorISBN(Long isbn) {
    return em.find(Libro.class, isbn);
  }

  public List<Libro> buscarLibrosPorTitulo(String titulo) {
    return em.createQuery("SELECT l FROM Libro l WHERE UPPER(l.titulo) LIKE UPPER(:titulo) AND l.alta = true", Libro.class)
        .setParameter("titulo", "%" + titulo + "%")
        .getResultList();
  }

  public List<Libro> buscarLibrosPorAutorId(Integer autorId) {
    return em.createQuery("SELECT l FROM Libro l WHERE l.autor.id = : id", Libro.class)
        .setParameter("id", autorId)
        .getResultList();
  }

  public List<Libro> buscarLibrosPorEditorialId(Integer editorialId) {
    return em.createQuery(
            "SELECT l FROM Libro l WHERE l.editorial.id = :id",
            Libro.class)
        .setParameter("id", editorialId)
        .getResultList();
  }

  public void darBajaLibro(Long isbn) {
    Libro libro = em.find(Libro.class, isbn);
    if (libro != null) {
      em.getTransaction().begin();
      libro.setAlta(false);
      em.merge(libro);
      em.getTransaction().commit();
    } else {
      throw new EntityNotFoundException("Libro con ISBN " + isbn + " no encontrado.");
    }
  }

  public void reactivarLibro(Long isbn) {
    Libro libro = em.find(Libro.class, isbn);
    if (libro != null) {
      em.getTransaction().begin();
      libro.setAlta(true);
      em.merge(libro);
      em.getTransaction().commit();
    } else {
      throw new EntityNotFoundException("Libro con ISBN " + isbn + " no encontrado.");
    }
  }

  public void actualizarLibro(Libro libro){
    em.getTransaction().begin();
    em.merge(libro);
    em.getTransaction().commit();
  }

  public List<Libro> listarLibros() {
    return em.createQuery("SELECT l FROM Libro l WHERE l.alta = true", Libro.class).getResultList();
  }

  public List<Libro> listarTodosLosLibros() {
    return em.createQuery("SELECT l FROM Libro l", Libro.class).getResultList();
  }
}
