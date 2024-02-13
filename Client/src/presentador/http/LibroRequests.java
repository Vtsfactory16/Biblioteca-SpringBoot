package presentador.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import modelo.Libro;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibroRequests {
    public static void postLibro(Libro libro) throws Exception {
        String jsonResponse = HTTPRequests.postRequest(libro.toJSON(), Constants.BASE_URL+"libros"); // Petición http
        System.out.println(jsonResponse);
        JSONObject object = new JSONObject(jsonResponse);

        if (object.has("id")) {
            libro.setId(object.getInt("id")); // Actualizar la ID del usuario
        } else  {
            HTTPRequests.logError(object);
        }
    }

    public static void putLibro(Libro libro) throws Exception {
        String jsonResponse = HTTPRequests.putRequest(libro.toJSON(), Constants.BASE_URL+"libros/" + libro.getId()); // Petición http
        System.out.println(jsonResponse);
        JSONObject object = new JSONObject(jsonResponse);
        if (object.has("error")) {
            HTTPRequests.logError(object);
        }
    }

    public static List<Libro> getLibros() {
        String json = HTTPRequests.getRequest(Constants.BASE_URL + "libros/");
        // TODO: json to user list
        //una jar para mappear cosas, en este caso json a la clase que tenemos
        ObjectMapper objectMapper = new ObjectMapper();


        List<Libro> libroList = new ArrayList<>();

        try {

            Libro[] libros = objectMapper.readValue(json, Libro[].class);


            for (Libro libro : libros) {
                libroList.add(libro);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return libroList;
    }

    public static void deleteLibro(Libro usuario) throws Exception {
        String jsonResponse = HTTPRequests.deleteRequest(Constants.BASE_URL + "libros/" + usuario.getId() );
        JSONObject object = new JSONObject(jsonResponse);
        if (object.has("error")) {
            HTTPRequests.logError(object);
        }
    }
}
