package org.example.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "editorial")
public class Editorial {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer idEditorial;

  @Column(name = "nombre")
  private String nombre;

  @Column(name = "alta")
  private boolean alta;

  public Integer getIdEditorial() {
    return idEditorial;
  }

  public void setIdEditorial(Integer idEditorial) {
    this.idEditorial = idEditorial;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public boolean isAlta() {
    return alta;
  }

  public void setAlta(boolean alta) {
    this.alta = alta;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Editorial)) return false;
    Editorial that = (Editorial) o;
    return idEditorial != null && idEditorial.equals(that.idEditorial);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

}
