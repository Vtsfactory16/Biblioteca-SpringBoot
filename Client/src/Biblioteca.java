import com.formdev.flatlaf.FlatDarkLaf;
import modelo.http.Login;
import singleton.Configuracion;
import vista.FormMain;

import javax.swing.*;

public class Biblioteca {
    public static void main(String[] args) {

        Runnable r = () -> {
            try {
                Login.disconect(Configuracion.getInstance().getUser());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
        Runtime.getRuntime().addShutdownHook(new Thread(r));
        FlatDarkLaf.setup();
        SwingUtilities.invokeLater(() -> FormMain.getInstance().setVisible(true));
    }
}
