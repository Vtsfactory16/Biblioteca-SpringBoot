package modelo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Objects;

public class Usuario implements CsvSerializable{
    private int id;
    private String nombre;
    private String apellidos;
    private Collection<Prestamo> prestamosById;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String apellidos) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Usuario(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

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
        Usuario that = (Usuario) o;
        return id == that.id && Objects.equals(nombre, that.nombre) && Objects.equals(apellidos, that.apellidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellidos);
    }

    public Collection<Prestamo> getPrestamosById() {
        return prestamosById;
    }

    public void setPrestamosById(Collection<Prestamo> prestamosById) {
        this.prestamosById = prestamosById;
    }

    public String toJSON() throws JSONException {
        return new JSONObject()
                .put("id", id)
                .put("nombre", nombre)
                .put("apellidos",apellidos)
                .toString();
    }

    @Override
    public String toString() {
        return nombre + " " + apellidos;
    }

    @Override
    public String toCsv() {
        return String.format("%d, %s, %s", id, nombre, apellidos);
    }

    @Override
    public String getCsvHeader() {
        return "id, nombre, apellidos";
    }
}
