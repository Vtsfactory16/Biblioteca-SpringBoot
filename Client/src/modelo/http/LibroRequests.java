package modelo.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import modelo.Libro;
import modelo.Prestamo;
import modelo.Usuario;
import modelo.dao.LibroDAO;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class LibroRequests implements LibroDAO {
    @Override
    public void insert(Libro libro) throws Exception {
        String jsonResponse = HTTPRequests.postRequest(libro.toJSON(), Constants.BASE_URL + "libros"); // Petición http
        JSONObject object = new JSONObject(jsonResponse);
        libro.setId(object.getInt("id"));
    }

    @Override
    public void update(Libro libro) throws Exception {
        String jsonResponse = HTTPRequests.putRequest(libro.toJSON(), Constants.BASE_URL + "libros/" + libro.getId()); // Petición http
        JSONObject object = new JSONObject(jsonResponse);
        HTTPRequests.throwException(object);
    }

    @Override
    public void delete(int id) throws Exception {
        HTTPRequests.deleteRequest(Constants.BASE_URL + "libros/" + id);
        // si el código de cualquier petición es distinto a 200, se lanza una excepción,
        // luego no es necesario hacer más comprobaciones aquí.
    }


    @Override
    public List<Libro> getAll() throws Exception {
        String json = HTTPRequests.getRequest(Constants.BASE_URL + "libros");
        return jsonToLibroList(json);
    }

    @Override
    public List<Libro> getFiltered(int id, String nombre, String autor, String editorial, int idCategoria) throws Exception {
        StringBuilder uri = new StringBuilder(Constants.BASE_URL + "libros/filter");
        HTTPRequests.addParam(uri, "id", String.valueOf(id));
        HTTPRequests.addParam(uri, "nombre", nombre);
        HTTPRequests.addParam(uri, "autor", autor);
        HTTPRequests.addParam(uri, "editorial", editorial);
        HTTPRequests.addParam(uri, "idcategoria", String.valueOf(idCategoria));

        String json = HTTPRequests.getRequest(uri.toString());
        return jsonToLibroList(json);
    }

    private List<Libro> jsonToLibroList(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Libro[] libros = objectMapper.readValue(json, Libro[].class);
        return Arrays.asList(libros);
    }

    @Override
    public Libro getById(int id) throws Exception {
        String json = HTTPRequests.getRequest(Constants.BASE_URL + "libros/" + id);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Libro.class);
    }

}
