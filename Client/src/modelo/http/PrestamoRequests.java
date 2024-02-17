package modelo.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import modelo.Prestamo;
import modelo.dao.PrestamoDAO;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class PrestamoRequests implements PrestamoDAO {
    @Override
    public void insert(Prestamo prestamo) throws Exception {
        String jsonResponse = HTTPRequests.postRequest(prestamo.toJSON(), Constants.BASE_URL + "prestamos");
        JSONObject object = new JSONObject(jsonResponse);
        prestamo.setIdPrestamo(object.getInt("idPrestamo")); // actualizar la ID del prestamo
    }

    @Override
    public void update(Prestamo prestamo) throws Exception {
        HTTPRequests.putRequest(prestamo.toJSON(), Constants.BASE_URL+"prestamos/" + prestamo.getIdPrestamo());
    }

    @Override
    public List<Prestamo> getAll() throws Exception{
        String jsonResponse = HTTPRequests.getRequest(Constants.BASE_URL + "prestamos");
        ObjectMapper objectMapper = new ObjectMapper();
        Prestamo[] prestamos = objectMapper.readValue(jsonResponse, Prestamo[].class);
        return Arrays.asList(prestamos);
    }

    @Override
    public Prestamo getById(int id) throws Exception {
        return null;
    }

    @Override
    public void delete(int id) throws Exception {
        HTTPRequests.deleteRequest(Constants.BASE_URL + "prestamos/" + id);
    }
}
