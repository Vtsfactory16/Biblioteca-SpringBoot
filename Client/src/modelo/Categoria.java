package modelo;


import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Objects;

public class Categoria implements CsvSerializable{
    private int id;
    private String categoria;
    private Collection<Libro> libros;

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
        Categoria that = (Categoria) o;
        return id == that.id && Objects.equals(categoria, that.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoria);
    }

    public Collection<Libro> getLibros() {
        return libros;
    }

    public void setLibros(Collection<Libro> libros) {
        this.libros = libros;
    }

    public String toJson() throws Exception {
        return new JSONObject()
                .put("id", id)
                .put("categoria", categoria).
                toString();
    }

    @Override
    public String toString() {
        return categoria;
    }

    @Override
    public String toCsv() {
        return String.format("%d, %s", id, categoria);
    }

    @Override
    public String getCsvHeader() {
        return "id, categoria";
    }
}
