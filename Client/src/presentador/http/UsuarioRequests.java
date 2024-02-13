package presentador.http;

import modelo.Usuario;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRequests {
    public static void postUser(Usuario usuario) throws Exception {
        String jsonResponse = HTTPRequests.postRequest(usuario.toJSON(), Constants.BASE_URL+"usuarios"); // Petición http
        JSONObject object = new JSONObject(jsonResponse);
        if (object.has("id")) {
            usuario.setId(object.getInt("id")); // Actualizar la ID del usuario
        } else  {
            HTTPRequests.logError(object);
        }
    }

    public static void putUser(Usuario usuario) throws Exception {
        String jsonResponse = HTTPRequests.putRequest(usuario.toJSON(), Constants.BASE_URL+"usuarios/" + usuario.getId()); // Petición http
        JSONObject object = new JSONObject(jsonResponse);
        if (object.has("error")) {
            HTTPRequests.logError(object);
        }
    }

    public static List<Usuario> getUsers()  throws Exception{
        String json = HTTPRequests.getRequest(Constants.BASE_URL + "usuarios/");
        // TODO: json to user list
        return new ArrayList<>();
    }

    public static void deleteUser(Usuario usuario) throws Exception {
        String jsonResponse = HTTPRequests.deleteRequest(Constants.BASE_URL + "usuarios/" + usuario.getId() );
        JSONObject object = new JSONObject(jsonResponse);
        if (object.has("error")) {
            HTTPRequests.logError(object);
        }
    }
}
