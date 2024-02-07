package com.example.bibliotecaspringboot.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "prestamos", schema = "BIBLIOTECA")
public class PrestamosDTO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idprestamo", nullable = false)
    private int idPrestamo;
    @Basic
    @Column(name = "fechaprestamo", nullable = true)
    private Timestamp fechaPrestamo;
    @ManyToOne
    @JoinColumn(name = "idlibro", referencedColumnName = "id")
    @JsonIgnoreProperties("prestamos")
    private LibroDTO libro;
    @ManyToOne
    @JoinColumn(name = "idusuario", referencedColumnName = "id")
    @JsonIgnoreProperties("prestamos")
    private UsuarioDTO usuario;

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Timestamp getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Timestamp fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrestamosDTO that = (PrestamosDTO) o;
        return idPrestamo == that.idPrestamo && Objects.equals(fechaPrestamo, that.fechaPrestamo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPrestamo, fechaPrestamo);
    }

    public LibroDTO getLibro() {
        return libro;
    }

    public void setLibro(LibroDTO libro) {
        this.libro = libro;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
}
