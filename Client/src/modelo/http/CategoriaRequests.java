package modelo.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import modelo.Categoria;
import modelo.dao.CategoriaDAO;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class CategoriaRequests implements CategoriaDAO {


    @Override
    public void insert(Categoria categoria) throws Exception {
        String jsonResponse = HTTPRequests.postRequest(categoria.toJson(), Constants.BASE_URL+"categorias"); // Petición http
        JSONObject object = new JSONObject(jsonResponse);
        categoria.setId(object.getInt("id")); // Actualizar la ID del usuario
    }

    @Override
    public void update(Categoria categoria) throws Exception {
        HTTPRequests.putRequest(categoria.toJson(), Constants.BASE_URL+"categorias/" + categoria.getId()); // Petición http
    }

    @Override
    public List<Categoria> getAll()  throws Exception {
        String json = HTTPRequests.getRequest(Constants.BASE_URL + "categorias");
        ObjectMapper objectMapper = new ObjectMapper();
        Categoria[] categorias = objectMapper.readValue(json, Categoria[].class);
        return Arrays.asList(categorias);
    }

    @Override
    public void delete(int id) throws Exception {
        HTTPRequests.deleteRequest(Constants.BASE_URL + "categorias/" + id);
    }

    @Override
    public Categoria getById(int id) throws Exception {
        String json = HTTPRequests.getRequest(Constants.BASE_URL + "categorias/" + id);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Categoria.class);
    }

}
