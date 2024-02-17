package com.example.bibliotecaspringboot.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "usuario", schema = "BIBLIOTECA")
public class UsuarioDTO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @NotBlank(message="El campo \"nombre\" no puede estar vacío")
    @Size(min = 2, max = 20, message = "El nombre tiene que tener entre 2 y 20 caracteres")
    @Column(name = "nombre", nullable = true, length = -1)
    private String nombre;
    @Basic
    @NotBlank(message="El campo \"apellidos\" no puede estar vacío")
    @Size(min = 2, max = 20, message = "Los apellidos tiene que tener entre 2 y 20 caracteres")
    @Column(name = "apellidos", nullable = true, length = -1)
    private String apellidos;
    @OneToMany(mappedBy = "usuario")
    @JsonIgnoreProperties({"usuario"})

    private Collection<PrestamosDTO> prestamosById;

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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioDTO that = (UsuarioDTO) o;
        return id == that.id && Objects.equals(nombre, that.nombre) && Objects.equals(apellidos, that.apellidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellidos);
    }

    public Collection<PrestamosDTO> getPrestamosById() {
        return prestamosById;
    }

    public void setPrestamosById(Collection<PrestamosDTO> prestamosById) {
        this.prestamosById = prestamosById;
    }
}
