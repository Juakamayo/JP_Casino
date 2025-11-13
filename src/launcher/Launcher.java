package launcher;

import controlador.SessionController;
import vista.VentanaLogin;
import javax.swing.*;

public class Launcher {
    public static void main(String[] args) {

        SessionController sessionController = new SessionController();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            sessionController.guardarDatosSesion();
        }));

        SwingUtilities.invokeLater(() -> {
            new VentanaLogin(sessionController);
        });
    }
}