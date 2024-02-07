package com.example.bibliotecaspringboot.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "libro", schema = "BIBLIOTECA" )
public class LibroDTO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @NotBlank(message="El nombre no puede estar vacío")
    @Size(min = 2, max = 40, message = "El nombre tiene que tener entre 2 y 40 caracteres")
    @Column(name = "nombre", nullable = true, length = -1)
    private String nombre;
    @Basic
    @NotBlank(message="El autor no puede estar vacío")
    @Size(min = 2, max = 20, message = "El nombre tiene que tener entre 2 y 20 caracteres")
    @Column(name = "autor", nullable = true, length = -1)
    private String autor;
    @Basic
    @NotBlank(message="La editorial no puede estar vacío")
    @Size(min = 2, max = 20, message = "El nombre tiene que tener entre 2 y 20 caracteres")
    @Column(name = "editorial", nullable = true, length = -1)
    private String editorial;
    @ManyToOne
    @JoinColumn(name = "categoria", referencedColumnName = "id")
    @JsonIgnoreProperties("libros")
    private CategoriaDTO categoria;
    @OneToMany(mappedBy = "libro")
    @JsonIgnoreProperties("libro")
    private Collection<PrestamosDTO> prestamos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibroDTO libroDTO = (LibroDTO) o;
        return id == libroDTO.id && Objects.equals(nombre, libroDTO.nombre) && Objects.equals(autor, libroDTO.autor) && Objects.equals(editorial, libroDTO.editorial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, autor, editorial);
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }

    public Collection<PrestamosDTO> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(Collection<PrestamosDTO> prestamos) {
        this.prestamos = prestamos;
    }
}
