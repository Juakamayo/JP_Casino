package launcher;

import controlador.SessionController;
import vista.VentanaLogin;
import javax.swing.*;

public class Launcher {
    public static void main(String[] args) {

        SessionController sessionController = new SessionController();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            sessionController.guardarUsuarios();
        }));

        SwingUtilities.invokeLater(() -> {
            new VentanaLogin(sessionController);
        });
    }
}