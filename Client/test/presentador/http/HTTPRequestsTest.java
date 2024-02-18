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

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    void postInvalidUsuario() {
        Usuario usuario = new Usuario(0, "Sippy", "B");
        assertThrows(Exception.class, () -> new UsuarioRequests().insert(usuario));
    }

    @Test
    void postValidPrestamo() throws Exception {
        Prestamo prestamo = new Prestamo();
        Libro libro = new LibroRequests().getAll().get(0);
        Usuario usuario = new UsuarioRequests().getAll().get(0);
        prestamo.setLibro(libro);
        prestamo.setUsuario(usuario);
        prestamo.setFechaPrestamo(Timestamp.valueOf(LocalDateTime.now()));
        assertDoesNotThrow(() -> new PrestamoRequests().insert(prestamo));
    }

    @Test
    void postInvalidPrestamo() throws Exception {
        Prestamo prestamo = new Prestamo();
        assertThrows(Exception.class, () -> new PrestamoRequests().insert(prestamo));
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

        assertDoesNotThrow(() -> objectMapper.readValue(jsonString, Libro[].class));
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
        prestamo.setLibro(new LibroRequests().getAll().get(0));
        prestamo.setUsuario(new UsuarioRequests().getAll().get(0));
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
    void insertCategoria() throws Exception {
        Categoria categoria = new Categoria();
        categoria.setCategoria("enemies to lovers");
        new CategoriaRequests().insert(categoria);
        assertNotEquals(0, categoria.getId());
    }

    @Test
    void insertInvalidCategoria() throws Exception {
        Categoria categoria = new Categoria();
        categoria.setCategoria("a");
        assertThrows(Exception.class, () -> new CategoriaRequests().insert(categoria));
    }


    @Test
    void configTest() throws Exception {
        String url = Configuracion.getInstance().getUrl();
        assertEquals( "http://localhost:8080/biblioteca/", url );
    }

    @Test
    void getUsuariosORTest() throws Exception {
        UsuarioDAO requests = new UsuarioRequests();
        List<Usuario> users = requests.getFiltered(1,"","");
        System.out.println(users);
    }

    @Test
    void getLibrosORTest() throws Exception {
        LibroDAO requests = new LibroRequests();
        List<Libro> libros = requests.getFiltered(0,"a","", "", 0);
        System.out.println(libros);
    }
}

