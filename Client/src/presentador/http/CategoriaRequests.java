package presentador.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import modelo.Categoria;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class CategoriaRequests {


    public static void postCategoria(Categoria categoria) throws Exception {
        String jsonResponse = HTTPRequests.postRequest(categoria.toJson(), Constants.BASE_URL+"categorias"); // Petición http
        JSONObject object = new JSONObject(jsonResponse);
        categoria.setId(object.getInt("id")); // Actualizar la ID del usuario
    }

    public static void putCategoria(Categoria categoria) throws Exception {
        HTTPRequests.putRequest(categoria.toJson(), Constants.BASE_URL+"categorias/" + categoria.getId()); // Petición http
    }

    public static List<Categoria> getCategorias()  throws Exception {
        String json = HTTPRequests.getRequest(Constants.BASE_URL + "categorias");
        ObjectMapper objectMapper = new ObjectMapper();
        Categoria[] categorias = objectMapper.readValue(json, Categoria[].class);
        return Arrays.asList(categorias);
    }

    public static void deleteCategoria(Categoria categoria) throws Exception {
        HTTPRequests.deleteRequest(Constants.BASE_URL + "categorias/" + categoria.getId() );
    }

}
