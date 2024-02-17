package presentador.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import modelo.Libro;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LibroRequests {
    public static void postLibro(Libro libro) throws Exception {
        String jsonResponse = HTTPRequests.postRequest(libro.toJSON(), Constants.BASE_URL+"libros"); // Petición http
        JSONObject object = new JSONObject(jsonResponse);
        libro.setId(object.getInt("id"));
    }

    public static void putLibro(Libro libro) throws Exception {
        String jsonResponse = HTTPRequests.putRequest(libro.toJSON(), Constants.BASE_URL+"libros/" + libro.getId()); // Petición http
        JSONObject object = new JSONObject(jsonResponse);
        HTTPRequests.throwException(object);
    }

    public static List<Libro> getLibros() throws Exception {
        String json = HTTPRequests.getRequest(Constants.BASE_URL + "libros");
        ObjectMapper objectMapper = new ObjectMapper(); // Pasar de json a objetos
        Libro[] libros = objectMapper.readValue(json, Libro[].class);
        return Arrays.asList(libros);
    }

    public static void deleteLibro(Libro usuario) throws Exception {
        HTTPRequests.deleteRequest(Constants.BASE_URL + "libros/" + usuario.getId() );
        // si el código de cualquier petición es distinto a 200, se lanza una excepción,
        // luego no es necesario hacer más comprobaciones aquí.
    }
}
