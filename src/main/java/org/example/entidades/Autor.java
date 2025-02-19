package org.example.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "autor")
public class Autor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer idAutor;

  @Column(name = "nombre")
  private String nombre;

  @Column(name = "alta")
  private boolean alta;

  public Integer getIdAutor() {
    return idAutor;
  }

  public void setIdAutor(Integer idAutor) {
    this.idAutor = idAutor;
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
}
