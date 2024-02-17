package presentador.http;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


/**
 * Realiza peticiones HTTP.
 * estos métodos devuelven un Json válido o lanzan una excepción si el código de
 * respuesta es distinto a 200
 */
public class HTTPRequests {
    static HttpClient client = HttpClient.newHttpClient();

    public static String postRequest(String json, String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json; utf-8")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        checkResponse(response);
        // si en este punto no se ha lanzado ninguna excepción, response.body() es un json válido
        return response.body();
    }


    public static String putRequest(String json, String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json; utf-8")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        checkResponse(response);
        // si en este punto no se ha lanzado ninguna excepción, response.body() es un json válido
        return response.body();
    }

    public static String getRequest(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        checkResponse(response);
        // si en este punto no se ha lanzado ninguna excepción, response.body() es un json válido
        return response.body();
    }

    public static String deleteRequest(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        checkResponse(response);
        // si en este punto no se ha lanzado ninguna excepción, response.body() es un json válido
        return response.body();
    }

    /**
     * Lanza una excepción si el código de respuesta no es 200,
     * de esta forma evitamos tener que manejar errores en las clases
     * LibroRequests, PrestamoRequests, CategoriaRequests y UsuarioRequests
     */
    private static void checkResponse(HttpResponse<String> postResponse) throws Exception {
        if (postResponse.statusCode() != 200) {
            // si ha devuelto información en el body en forma de json, lo incluimos en el error
            if (isValidJson(postResponse.body())) {
                throwException(new JSONObject(postResponse.body()));
            }
            // si no, simplemente incluimos el código de respuesta
            throw new IOException("HTTP request failed with response status code " + postResponse.statusCode() + ".");
        }
    }

    /**
     * Devuelve true si el String es un json válido
     */
    private static boolean isValidJson(String string) {
        try {
            new JSONObject(string);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    /**
     * Lanza una excepción que incluye la información devuelta por el servidor
     */
    public static void throwException(JSONObject object) throws Exception {
        String linea1 = String.format("Error: %s (%s)\n%s", object.get("error"), object.get("status"), object.get("message"));
        if (object.has("errors")) {
            JSONArray jsonArray = object.getJSONArray("errors");
            String linea2 = jsonArray.getJSONObject(0).get("defaultMessage").toString();
            throw new Exception(String.format("%s\n%s\n", linea1, linea2));
        } else {
            throw new Exception(String.format("%s\n", linea1));
        }
    }
}