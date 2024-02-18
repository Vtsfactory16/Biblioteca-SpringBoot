import com.formdev.flatlaf.FlatDarkLaf;
import vista.FormMain;

import javax.swing.*;

public class Biblioteca {
    public static void main(String[] args) {
        FlatDarkLaf.setup();
        SwingUtilities.invokeLater(() -> FormMain.getInstance().setVisible(true));
    }
}
