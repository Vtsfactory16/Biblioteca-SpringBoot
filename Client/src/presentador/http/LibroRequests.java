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

        if (object.has("id")) {
            libro.setId(object.getInt("id")); // Actualizar la ID del usuario
        } else  {
            HTTPRequests.throwException(object);
        }
    }

    public static void putLibro(Libro libro) throws Exception {
        String jsonResponse = HTTPRequests.putRequest(libro.toJSON(), Constants.BASE_URL+"libros/" + libro.getId()); // Petición http
        JSONObject object = new JSONObject(jsonResponse);
        if (object.has("error")) {
            HTTPRequests.throwException(object);
        }
    }

    public static List<Libro> getLibros() throws Exception {
        String json = HTTPRequests.getRequest(Constants.BASE_URL + "libros");

        ObjectMapper objectMapper = new ObjectMapper(); // Pasar de json a objetos
        Libro[] libros = objectMapper.readValue(json, Libro[].class);
        return Arrays.asList(libros);
    }

    public static void deleteLibro(Libro usuario) throws Exception {
        String jsonResponse = HTTPRequests.deleteRequest(Constants.BASE_URL + "libros/" + usuario.getId() );
        JSONObject object = new JSONObject(jsonResponse);
        if (object.has("error")) {
            HTTPRequests.throwException(object);
        }
    }
}
