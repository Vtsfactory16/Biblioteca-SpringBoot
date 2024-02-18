package com.example.bibliotecaspringboot.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "bibliotecarios", schema = "biblioteca")
public class BibliotecariosDTO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "usuario")
    private String usuario;
    @Basic
    @Column(name = "clave")
    private String clave;
    @Basic
    @Column(name = "activo")
    private Byte activo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Byte getActivo() {
        return activo;
    }

    public void setActivo(Byte activo) {
        this.activo = activo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BibliotecariosDTO that = (BibliotecariosDTO) o;

        if (id != that.id) return false;
        if (usuario != null ? !usuario.equals(that.usuario) : that.usuario != null) return false;
        if (clave != null ? !clave.equals(that.clave) : that.clave != null) return false;
        if (activo != null ? !activo.equals(that.activo) : that.activo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (usuario != null ? usuario.hashCode() : 0);
        result = 31 * result + (clave != null ? clave.hashCode() : 0);
        result = 31 * result + (activo != null ? activo.hashCode() : 0);
        return result;
    }
}
