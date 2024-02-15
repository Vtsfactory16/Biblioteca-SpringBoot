package presentador.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import modelo.Libro;
import modelo.Prestamo;
import modelo.Usuario;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import vista.helper.Libros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HTTPRequestsTest {
    @Test
    void postAndPutRequest() {
        Usuario usuario = new Usuario(1, "Sippy", "Buch");
    }

    @Test
    void getLibros() throws Exception {
        System.out.println(LibroRequests.getLibros());
    }

    @Test
    void jsonToList() {

        // Sample JSON string
        String jsonString = "[{\"id\": 1, \"nombre\": \"Book1\", \"autor\": \"Author1\", \"editorial\": \"Publisher1\"}, {\"id\": 2, \"nombre\": \"Book2\", \"autor\": \"Author2\", \"editorial\": \"Publisher2\"}]";

        // Initialize ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        // Create a list to store instances of Libro
        List<Libro> libroList = new ArrayList<>();

        try {
            // Parse JSON string into an array of Libro objects
            Libro[] libros = objectMapper.readValue(jsonString, Libro[].class);

            // Add Libro objects to the list
            for (Libro libro : libros) {
                libroList.add(libro);
            }

            // Now libroList contains instances of Libro
            for (Libro libro : libroList) {
                System.out.println("ID: " + libro.getId() + ", Nombre: " + libro.getNombre() + ", Autor: " + libro.getAutor() + ", Editorial: " + libro.getEditorial());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getPrestamosTest() throws Exception {
        for (Prestamo p : PrestamoRequests.getPrestamos()) {
            System.out.println(p.getIdPrestamo());
            System.out.println(p.getUsuario());
            System.out.println(p.getLibro());
            System.out.println(p.getFechaPrestamo());
        }
    }

    @Test
    void postPrestamo() throws Exception {
        String json = "{\"libro\":{\"id\":5},\"fechaPrestamo\":\"2024-02-15 19:47:50.6930963\",\"idPrestamo\":0,\"usuario\":{\"id\":2}}";
        String response = HTTPRequests.postRequest(json, "http://localhost:8080/biblioteca/prestamos");
        JSONObject jsonResponse = new JSONObject(response);
        System.out.println(response);
        assertTrue(jsonResponse.has("fechaPrestamo"));
        assertNotNull(jsonResponse.get("fechaPrestamo"));
    }

    @Test
    void postPrestamoObject() throws Exception {
        Prestamo prestamo = new Prestamo(); // default timestamp is now
        String response = HTTPRequests.postRequest(prestamo.toJSON(), "http://localhost:8080/biblioteca/prestamos");
        JSONObject jsonResponse = new JSONObject(response);
        System.out.println(jsonResponse.get("fechaPrestamo"));
        assertNotEquals("null", jsonResponse.get("fechaPrestamo"));

    }
}

