package presentador.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import modelo.Usuario;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UsuarioRequests {
    public static void postUser(Usuario usuario) throws Exception {
        String jsonResponse = HTTPRequests.postRequest(usuario.toJSON(), Constants.BASE_URL+"usuarios"); // Petición http
        JSONObject object = new JSONObject(jsonResponse);
        usuario.setId(object.getInt("id")); // Actualizar la ID del usuario
    }

    public static void putUser(Usuario usuario) throws Exception {
        HTTPRequests.putRequest(usuario.toJSON(), Constants.BASE_URL+"usuarios/" + usuario.getId()); // Petición http
    }

    public static List<Usuario> getUsers()  throws Exception{
        String json = HTTPRequests.getRequest(Constants.BASE_URL + "usuarios");
        ObjectMapper objectMapper = new ObjectMapper();
        Usuario[] usuarios = objectMapper.readValue(json, Usuario[].class);
        return Arrays.asList(usuarios);
    }

    public static void deleteUser(Usuario usuario) throws Exception {
        String jsonResponse = HTTPRequests.deleteRequest(Constants.BASE_URL + "usuarios/" + usuario.getId() );
    }
}
