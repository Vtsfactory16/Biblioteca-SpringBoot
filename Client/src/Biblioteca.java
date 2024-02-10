import vista.FormMain;

import javax.swing.*;

public class Biblioteca {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> FormMain.getInstance().setVisible(true));
    }
}
