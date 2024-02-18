package modelo.http;

import org.json.JSONObject;

public class Login{


    public static boolean loginCheck(String user, String pass){
        try {
            String json = HTTPRequests.getRequest(Constants.BASE_URL + "bibliotecarios/" + user);
            JSONObject jsonObject = new JSONObject(json);
            if (pass.equals(jsonObject.getString("clave"))){
                jsonObject.put("activo", 1);
                System.out.println(jsonObject);
                HTTPRequests.putRequest(jsonObject.toString(),Constants.BASE_URL + "bibliotecarios/" + user);
                return true;
            }

        } catch (Exception e) {

            return false;
        }
       return false;
    }
    public static boolean disconect(String user){
        try {
            String json = HTTPRequests.getRequest(Constants.BASE_URL + "bibliotecarios/" + user);
            JSONObject jsonObject = new JSONObject(json);
            jsonObject.put("activo", 0);
            System.out.println(jsonObject);
            HTTPRequests.putRequest(jsonObject.toString(),Constants.BASE_URL + "bibliotecarios/" + user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
