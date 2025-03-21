package org.example.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "libro")
public class Libro {
  @Id
  @Column(name = "isbn")
  private Long isbn;

  @Column(name = "titulo")
  private String titulo;

  @Column(name = "anio")
  private Integer anio;

  @Column(name = "ejemplares")
  private Integer ejemplares;

  @Column(name = "alta")
  private boolean alta;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "autor", referencedColumnName = "id", nullable = false,
      foreignKey = @jakarta.persistence
          .ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
  private Autor autor;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "editorial", referencedColumnName = "id", nullable = false,
      foreignKey = @jakarta.persistence
          .ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
  private Editorial editorial;

  public Long getIsbn() {
    return isbn;
  }

  public void setIsbn(Long isbn) {
    this.isbn = isbn;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public Integer getAnio() {
    return anio;
  }

  public void setAnio(Integer anio) {
    this.anio = anio;
  }

  public Integer getEjemplares() {
    return ejemplares;
  }

  public void setEjemplares(Integer ejemplares) {
    this.ejemplares = ejemplares;
  }

  public boolean isAlta() {
    return alta;
  }

  public void setAlta(boolean alta) {
    this.alta = alta;
  }

  public Autor getAutor() {
    return autor;
  }

  public void setAutor(Autor autor) {
    this.autor = autor;
  }

  public Editorial getEditorial() {
    return editorial;
  }

  public void setEditorial(Editorial editorial) {
    this.editorial = editorial;
  }
}
