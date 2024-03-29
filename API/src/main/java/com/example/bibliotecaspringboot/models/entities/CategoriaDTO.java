package com.example.bibliotecaspringboot.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "categoria", schema = "BIBLIOTECA")
public class CategoriaDTO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "categoria", nullable = true, length = -1)
    @NotBlank(message="La categoria no puede estar vacía")
    @Size(min = 2, max = 20, message = "La categoria debe tener entre 2 y 20 caracteres")
    private String categoria;
    @OneToMany(mappedBy = "categoria")
    @JsonIgnoreProperties("categoria")
    private Collection<LibroDTO> libros;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriaDTO that = (CategoriaDTO) o;
        return id == that.id && Objects.equals(categoria, that.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoria);
    }

    public Collection<LibroDTO> getLibros() {
        return libros;
    }

    public void setLibros(Collection<LibroDTO> libros) {
        this.libros = libros;
    }


}
