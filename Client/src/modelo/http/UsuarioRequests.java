package modelo.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import modelo.Usuario;
import modelo.dao.UsuarioDAO;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

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
    public Usuario getById(int id) throws Exception {
        return null;
    }

    @Override
    public List<Usuario> getAll() throws Exception{
        String json = HTTPRequests.getRequest(Constants.BASE_URL + "usuarios");
        ObjectMapper objectMapper = new ObjectMapper();
        Usuario[] usuarios = objectMapper.readValue(json, Usuario[].class);
        return Arrays.asList(usuarios);
    }

    @Override
    public List<Usuario> getFiltered(int id, String nombre, String apellidos) throws Exception {
        return null;
    }
}
