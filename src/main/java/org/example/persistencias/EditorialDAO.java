package org.example.persistencias;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import java.util.List;
import org.example.entidades.Editorial;

public class EditorialDAO {
  private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");
  private final EntityManager em = emf.createEntityManager();

  public void guardarEditorial(Editorial editorial) {
    if (!existeEditorialPorNombre(editorial.getNombre())) {
      em.getTransaction().begin();
      em.persist(editorial);
      em.getTransaction().commit();
    } else {
      throw new IllegalArgumentException("La editorial con el nombre '" + editorial.getNombre() + "' ya existe.");
    }
  }

  public boolean existeEditorialPorNombre(String nombre) {
    Long count = em.createQuery("SELECT COUNT(e) FROM Editorial e WHERE e.nombre = :nombre", Long.class)
        .setParameter("nombre", nombre)
        .getSingleResult();
    return count > 0;
  }

  public Editorial buscarEditorial(Integer idEditorial){
    return em.find(Editorial.class, idEditorial);
  }

  public void actualizarEditorial(Editorial editorial){
    em.getTransaction().begin();
    em.merge(editorial);
    em.getTransaction().commit();
  }

  public Editorial buscarEditorialPorNombre(String nombre) {
    try {
      return em.createQuery("SELECT e FROM Editorial e WHERE UPPER(TRIM(e.nombre)) = UPPER(TRIM(:nombre))", Editorial.class)
          .setParameter("nombre", nombre)
          .getSingleResult();
    } catch (NoResultException e){
      throw new NoResultException("Editorial con el nombre '" + nombre + "' no encontrada.");
    }
  }

  public List<Editorial> buscarEditorialesPorNombre(String nombre) {
    return em.createQuery(
            "SELECT e FROM Editorial e WHERE UPPER(e.nombre) LIKE UPPER(:nombre)",
            Editorial.class)
        .setParameter("nombre", "%" + nombre.trim() + "%")
        .getResultList();
  }


  public void darBajaEditorial(Integer idEditorial) {
    Editorial editorial = em.find(Editorial.class, idEditorial);
    if (editorial != null) {
      em.getTransaction().begin();
      editorial.setAlta(false);
      em.merge(editorial);
      em.getTransaction().commit();
    } else {
      throw new EntityNotFoundException("Editorial con ID " + idEditorial + " no encontrada.");
    }
  }

  public void reactivarEditorial(Integer idEditorial) {
    Editorial editorial = em.find(Editorial.class, idEditorial);
    if (editorial != null) {
      em.getTransaction().begin();
      editorial.setAlta(true);
      em.merge(editorial);
      em.getTransaction().commit();
    } else {
      throw new EntityNotFoundException("Editorial con ID " + idEditorial + " no encontrada.");
    }
  }

  public List<Editorial> listarEditoriales() {
    return em.createQuery("SELECT e FROM Editorial e WHERE e.alta = true", Editorial.class).getResultList();
  }

  public List<Editorial> listarTodasLasEditoriales() {
    return em.createQuery("SELECT e FROM Editorial e", Editorial.class)
        .getResultList();
  }
}
