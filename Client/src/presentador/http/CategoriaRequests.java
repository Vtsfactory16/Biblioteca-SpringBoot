package presentador.http;

import modelo.Categoria;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRequests {


    public static String postCategoria(Categoria categoria) throws Exception {
        String jsonResponse = HTTPRequests.postRequest(categoria.toJson(), Constants.BASE_URL+"categorias"); // Petición http
        System.out.println(jsonResponse);
        JSONObject object = new JSONObject(jsonResponse);

        if (object.has("id")) {
            categoria.setId(object.getInt("id")); // Actualizar la ID del usuario
        } else  {
            HTTPRequests.logError(object);
        }
        return jsonResponse;
    }
    public static String putCategoria(Categoria categoria) throws Exception {
        String jsonResponse = HTTPRequests.putRequest(categoria.toJson(), Constants.BASE_URL+"categorias/" + categoria.getId()); // Petición http
        System.out.println(jsonResponse);
        JSONObject object = new JSONObject(jsonResponse);
        if (object.has("error")) {
            HTTPRequests.logError(object);
        }
        return jsonResponse;
    }
    public static List<Categoria> getCategorias()  throws Exception {
        String json = HTTPRequests.getRequest(Constants.BASE_URL + "categorias/");
        return new ArrayList<>();
    }

    public static void deleteCategoria(Categoria categoria) throws Exception {
        String jsonResponse = HTTPRequests.deleteRequest(Constants.BASE_URL + "categorias/" + categoria.getId() );
        JSONObject object = new JSONObject(jsonResponse);
        if (object.has("error")) {
            HTTPRequests.logError(object);
        }
    }


}
