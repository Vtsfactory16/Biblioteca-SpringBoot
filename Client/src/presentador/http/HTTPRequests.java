package presentador.http;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTPRequests {

    public static void logError(JSONObject object) throws Exception {
        String linea1 = String.format("Error: %s (%s)\n%s",object.get("error"), object.get("status"), object.get("message"));
        if (object.has("errors")) {
            JSONArray jsonArray = object.getJSONArray("errors");
            String linea2 = jsonArray.getJSONObject(0).get("defaultMessage").toString();
            throw new Exception(String.format("%s\n%s\n", linea1, linea2));
        } else throw new Exception(String.format("%s\n", linea1 ));

    }

    public static String postRequest(String json, String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json; utf-8")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> postResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        return postResponse.body();
    }


    public static String putRequest(String json, String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json; utf-8")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> postResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        return postResponse.body();
    }

    public static String getRequest(String url) {
        // TODO: Implement
        return "{}";
    }

    public static String deleteRequest(String url) {
        // TODO: Implement
        return "{}";
    }

}