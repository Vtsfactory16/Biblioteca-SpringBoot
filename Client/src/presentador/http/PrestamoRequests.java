package presentador.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import modelo.Prestamo;
import org.json.JSONObject;
import vista.helper.Prestamos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrestamoRequests {
    public static void postPrestamo(Prestamo prestamo) throws Exception {
        String jsonResponse = HTTPRequests.postRequest(prestamo.toJSON(), Constants.BASE_URL + "prestamos");
        JSONObject object = new JSONObject(jsonResponse);
        if (object.has("idPrestamo")) {
            prestamo.setIdPrestamo(object.getInt("idPrestamo")); // actualizar la ID del prestamo
        } else  {
            HTTPRequests.throwException(object);
        }
    }

    public static void putPrestamo(Prestamo prestamo) throws Exception {
        String jsonResponse = HTTPRequests.putRequest(prestamo.toJSON(), Constants.BASE_URL+"prestamos/" + prestamo.getIdPrestamo());
        JSONObject object = new JSONObject(jsonResponse);
        if (object.has("error")) {
            HTTPRequests.throwException(object);
        }
    }

    public static List<Prestamo> getPrestamos() throws Exception{
        String jsonResponse = HTTPRequests.getRequest(Constants.BASE_URL + "prestamos");
        ObjectMapper objectMapper = new ObjectMapper();
        Prestamo[] prestamos = objectMapper.readValue(jsonResponse, Prestamo[].class);
        return Arrays.asList(prestamos);
    }

    public static void deletePrestamo(Prestamo prestamo) throws Exception {
        String jsonResponse = HTTPRequests.deleteRequest(Constants.BASE_URL + "prestamos/" + prestamo.getIdPrestamo());
        JSONObject object = new JSONObject(jsonResponse);
        if (object.has("error")) {
            HTTPRequests.throwException(object);
        }
    }
}
