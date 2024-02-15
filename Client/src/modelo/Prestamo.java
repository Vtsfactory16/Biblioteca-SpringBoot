package modelo;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public class Prestamo {
    private int idPrestamo;
    private Timestamp fechaPrestamo = Timestamp.valueOf(LocalDateTime.now());
    private Libro libro;
    private Usuario usuario;

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
        Prestamo that = (Prestamo) o;
        return idPrestamo == that.idPrestamo && Objects.equals(fechaPrestamo, that.fechaPrestamo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPrestamo, fechaPrestamo);
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String toJSON() throws JSONException {
        JSONObject jsonPrestamo = new JSONObject()
                .put("idPrestamo", idPrestamo)
                .put("fechaPrestamo", fechaPrestamo.getTime());

        if (libro != null) {
            // Para insertar, solo necesitamos la id de estos objetos
            JSONObject jsonLibro = new JSONObject().put("id", libro.getId());
            jsonPrestamo.put("libro", jsonLibro);
        }
        if (usuario != null) {
            JSONObject jsonUsuario = new JSONObject().put("id", usuario.getId());
            jsonPrestamo.put("usuario", jsonUsuario);
        }
        return jsonPrestamo.toString();
    }
}
