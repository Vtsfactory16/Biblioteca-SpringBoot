package presentador.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import modelo.Categoria;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoriaRequests {


    public static String postCategoria(Categoria categoria) throws Exception {
        String jsonResponse = HTTPRequests.postRequest(categoria.toJson(), Constants.BASE_URL+"categorias"); // Petición http
        System.out.println(jsonResponse);
        JSONObject object = new JSONObject(jsonResponse);

        if (object.has("id")) {
            categoria.setId(object.getInt("id")); // Actualizar la ID del usuario
        } else  {
            HTTPRequests.throwException(object);
        }
        return jsonResponse;
    }

    public static String putCategoria(Categoria categoria) throws Exception {
        String jsonResponse = HTTPRequests.putRequest(categoria.toJson(), Constants.BASE_URL+"categorias/" + categoria.getId()); // Petición http
        System.out.println(jsonResponse);
        JSONObject object = new JSONObject(jsonResponse);
        if (object.has("error")) {
            HTTPRequests.throwException(object);
        }
        return jsonResponse;
    }

    public static List<Categoria> getCategorias()  throws Exception {
        String json = HTTPRequests.getRequest(Constants.BASE_URL + "categorias");
        ObjectMapper objectMapper = new ObjectMapper();
        Categoria[] categorias = objectMapper.readValue(json, Categoria[].class);
        return Arrays.asList(categorias);
    }

    public static void deleteCategoria(Categoria categoria) throws Exception {
        String jsonResponse = HTTPRequests.deleteRequest(Constants.BASE_URL + "categorias/" + categoria.getId() );
        JSONObject object = new JSONObject(jsonResponse);
        if (object.has("error")) {
            HTTPRequests.throwException(object);
        }
    }

}
