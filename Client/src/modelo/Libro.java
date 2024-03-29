package modelo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Objects;

public class Libro implements CsvSerializable {
    private int id;
    private String nombre;
    private String autor;
    private String editorial;
    private Categoria categoria;
    private Collection<Prestamo> prestamos;

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
        Libro libroDTO = (Libro) o;
        return id == libroDTO.id && Objects.equals(nombre, libroDTO.nombre) && Objects.equals(autor, libroDTO.autor) && Objects.equals(editorial, libroDTO.editorial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, autor, editorial);
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Collection<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(Collection<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public String getNombreCategoria() {
        return categoria.getCategoria();
    }

    public void setCategoriaId(int id) {
        categoria.setId(id);
    }

    public String toJSON() throws JSONException {
        JSONObject jsonLibro = new JSONObject()
                .put("id", id)
                .put("nombre", nombre)
                .put("autor",autor)
                .put("editorial",editorial);
        if (categoria != null) {
            JSONObject jsonCategoria = new JSONObject().put("id", categoria.getId());
            jsonLibro.put("categoria",jsonCategoria);
        }
        return jsonLibro.toString();
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public String toCsv() {
        return String.format("%d, %s, %s, %s, %d",id, nombre, autor, editorial, getIdCategoria());
    }

    private int getIdCategoria() {
        if (categoria == null)
            return -1;
        return categoria.getId();
    }

    @Override
    public String getCsvHeader() {
        return "id, nombre, autor, editorial, categoria";
    }
}
