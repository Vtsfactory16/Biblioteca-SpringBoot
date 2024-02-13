package presentador.http;

import modelo.Prestamo;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PrestamoRequests {
    public static void postPrestamo(Prestamo prestamo) throws Exception {
        String jsonResponse = HTTPRequests.postRequest(prestamo.toJSON(), Constants.BASE_URL + "prestamos/");
        JSONObject object = new JSONObject(jsonResponse);

        if (object.has("id")) {
            prestamo.setIdPrestamo(object.getInt("idPrestamo")); // actualizar la ID del prestamo
        } else  {
            HTTPRequests.logError(object);
        }
    }

    public static void putPrestamo(Prestamo prestamo) throws Exception {
        String jsonResponse = HTTPRequests.putRequest(prestamo.toJSON(), Constants.BASE_URL+"usuarios/" + prestamo.getIdPrestamo());
        JSONObject object = new JSONObject(jsonResponse);
        if (object.has("error")) {
            HTTPRequests.logError(object);
        }
    }

    public static List<Prestamo> getPrestamos(Prestamo prestamo) throws Exception{
        String jsonResponse = HTTPRequests.getRequest(Constants.BASE_URL + "prestamos/");
        JSONObject object = new JSONObject(jsonResponse);
        if (object.has("error")) {
            HTTPRequests.logError(object);
        }
        return new ArrayList<>();
    }

    public static void deletePrestamo(Prestamo prestamo) throws Exception {
        String jsonResponse = HTTPRequests.deleteRequest(Constants.BASE_URL + "prestamos/" + prestamo.getIdPrestamo());
        JSONObject object = new JSONObject(jsonResponse);
        if (object.has("error")) {
            HTTPRequests.logError(object);
        }
    }
}
