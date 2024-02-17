package modelo.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import modelo.Usuario;
import modelo.dao.UsuarioDAO;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import modelo.http.HTTPRequests;

public class UsuarioRequests implements UsuarioDAO {
    @Override
    public void insert(Usuario usuario) throws Exception {
        String jsonResponse = HTTPRequests.postRequest(usuario.toJSON(), Constants.BASE_URL+"usuarios"); // Petición http
        JSONObject object = new JSONObject(jsonResponse);
        usuario.setId(object.getInt("id")); // Actualizar la ID del usuario
    }

    @Override
    public void update(Usuario usuario) throws Exception {
        HTTPRequests.putRequest(usuario.toJSON(), Constants.BASE_URL+"usuarios/" + usuario.getId()); // Petición http
    }

    @Override
    public void delete(int id) throws Exception {
        HTTPRequests.deleteRequest(Constants.BASE_URL + "usuarios/" + id );
    }


    @Override
    public List<Usuario> getAll() throws Exception{
        String json = HTTPRequests.getRequest(Constants.BASE_URL + "usuarios");
        return jsonToUserList(json);
    }

    @Override
    public List<Usuario> getFiltered(int id, String nombre, String apellidos) throws Exception {
        StringBuilder uri = new StringBuilder(Constants.BASE_URL + "usuarios/filter");
        HTTPRequests.addParam(uri, "id", String.valueOf(id));
        HTTPRequests.addParam(uri, "nombre", nombre);
        HTTPRequests.addParam(uri, "apellidos", apellidos);

        String json = HTTPRequests.getRequest(uri.toString());
        return jsonToUserList(json);
    }

    private static List<Usuario> jsonToUserList(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Usuario[] usuarios = objectMapper.readValue(json, Usuario[].class);
        return Arrays.asList(usuarios);
    }


    @Override
    public Usuario getById(int id) throws Exception {
        String json = HTTPRequests.getRequest(Constants.BASE_URL + "usuarios/" + id);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Usuario.class);
    }
}
