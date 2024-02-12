package presentador.http;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Clase para las peticiones HTTP, igual que el código de las diapositivas
 * Hay que hacer cambios porque ahora mismo solo puede insertar usuarios
 */
public class HTTPRequests {
    private static String baseUrl = "http://localhost:8080/biblioteca/";

    public static boolean postRequest(String json, String endpoint) throws Exception {
        String insertUrl = baseUrl + endpoint;
        boolean insertado = false;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(insertUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = json.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            if (conn.getResponseCode() == 200)
                insertado = true;
            else postError(conn, insertUrl);
        } finally {
            if (conn != null)
                conn.disconnect();
        }
        return insertado;
    }

    private static void postError(HttpURLConnection conn,String pUrl) throws Exception {
        Scanner scanner = new Scanner(conn.getErrorStream());
        String response = scanner.useDelimiter("\\Z").next();
        scanner.close();
        JSONObject jsonObject = new JSONObject(response);
        String linea1 = String.format("Fallo desde: %s", pUrl);
        String linea2 = String.format("Error: %s (%d)\n%s",jsonObject.get("error"),
                jsonObject.get("status"),jsonObject.get("message"));
        if (jsonObject.has("errors")) {
            JSONArray jsonArray = jsonObject.getJSONArray("errors");
            String linea3 = jsonArray.getJSONObject(0).get("defaultMessage").toString();
            throw new Exception(String.format("%s\n%s\n%s\n", linea1, linea2,linea3));
        } else throw new Exception(String.format("%s\n%s\n", linea1,linea2 ));
    }
}
