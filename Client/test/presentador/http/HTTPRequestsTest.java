package presentador.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import modelo.Categoria;
import modelo.Libro;
import modelo.Prestamo;
import modelo.Usuario;
import modelo.dao.LibroDAO;
import modelo.dao.UsuarioDAO;
import modelo.http.*;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import singleton.Configuracion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HTTPRequestsTest {
    @Test
    void postUsuario() throws Exception {
        Usuario usuario = new Usuario(0, "Sippy", "Buch");
        new UsuarioRequests().insert(usuario);
        assertNotEquals(0,usuario.getId());
    }

    @Test
    void postUnvalidUsuario() throws Exception {
        Usuario usuario = new Usuario(0, "Sippy", "B");
        assertThrows(Exception.class, () -> new UsuarioRequests().insert(usuario));
    }

    @Test
    void getLibros() throws Exception {
        System.out.println(new LibroRequests().getAll());
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
        for (Prestamo p : new PrestamoRequests().getAll()) {
            System.out.println(p.getIdPrestamo());
            System.out.println(p.getUsuario());
            System.out.println(p.getLibro());
            System.out.println(p.getFechaPrestamo());
        }
    }

    @Test
    void postPrestamo() throws Exception {
        String json = "{\"libro\":{\"id\":5},\"fechaPrestamo\":\"2024-02-16T18:58:21.362+01:00\",\"idPrestamo\":0,\"usuario\":{\"id\":2}}";
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

    @Test
    void getCategoriaByIdTest() throws Exception {
        Categoria categoria = new CategoriaRequests().getById(1);
        System.out.println(categoria.toCsv());
        System.out.println(categoria.getLibros());
        assertNotNull(categoria);
    }

    @Test
    void configTest() throws Exception {
        String url = Configuracion.getInstance().getUrl();
        assertEquals( "http://localhost:8080/biblioteca/", url );
    }

    @Test
    void getUsuariosORTest() throws Exception {
        UsuarioDAO requests = new UsuarioRequests();
        List<Usuario> users = requests.getFiltered(0,"","");
        System.out.println(users);
    }

    @Test
    void getLibrosORTest() throws Exception {
        LibroDAO requests = new LibroRequests();
        List<Libro> users = requests.getFiltered(0,"","", "", 0);
        System.out.println(users);
    }
}

