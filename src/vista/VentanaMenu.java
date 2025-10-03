package vista;

import controlador.SessionController;

import javax.swing.*;
import java.awt.*;

public class VentanaMenu extends JFrame {

    // 1. Declaramos el campo 'session' aquí, en la clase principal
    private final SessionController session;
    private final String nombreUsuario;

    // (Hemos eliminado la clase anidada duplicada 'public class VentanaMenu { ... }')

    // El constructor ahora recibe el controlador y el nombre del usuario.
    public VentanaMenu(SessionController session, String nombreUsuario) {
        super("Menú Principal - Casino Black Cat");

        // 2. Asignamos los valores a los campos de la clase
        this.session = session;
        this.nombreUsuario = nombreUsuario;

        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ... (El resto del código permanece igual) ...
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        JLabel lblBienvenida = new JLabel("Bienvenido/a, " + nombreUsuario, SwingConstants.CENTER);
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton btnJugar = new JButton("Jugar Ruleta");
        JButton btnHistorial = new JButton("Historial");
        JButton btnSalir = new JButton("Cerrar Sesión");

        // Al cerrar sesión, usamos el controlador para invalidar la sesión
        btnSalir.addActionListener(e -> {
            this.session.cerrarSesion(); // ¡Ahora el controlador es accesible!
            dispose();
            new VentanaLogin();
        });

        // NOTA: Asegúrate de que VentanaJuego reciba los datos necesarios
        btnJugar.addActionListener(e -> {
            // new VentanaJuego(session, nombreUsuario); // Sugerencia: pasar 'session'
        });


        panelBotones.add(btnJugar);
        panelBotones.add(btnHistorial);
        panelBotones.add(btnSalir);

        add(lblBienvenida, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
    }
}