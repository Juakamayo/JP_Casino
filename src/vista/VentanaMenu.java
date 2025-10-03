package vista;

import controlador.SessionController;

import javax.swing.*;
import java.awt.*;

public class VentanaMenu extends JFrame {

    private final SessionController session;
    private final String nombreUsuario;



    public VentanaMenu(SessionController session, String nombreUsuario) {
        super("Menú Principal - Casino Black Cat");


        this.session = session;
        this.nombreUsuario = nombreUsuario;

        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        JLabel lblBienvenida = new JLabel("Bienvenido/a, " + nombreUsuario, SwingConstants.CENTER);
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton btnJugar = new JButton("Jugar Ruleta");
        JButton btnHistorial = new JButton("Historial");
        JButton btnSalir = new JButton("Cerrar Sesión");


        btnSalir.addActionListener(e -> {
            this.session.cerrarSesion();
            dispose();
            new VentanaLogin();
        });

        btnJugar.addActionListener(e -> {
            this.session.cerrarSesion();
            dispose();
            new VentanaJuego(nombreUsuario);

        });

        btnHistorial.addActionListener(e -> {
            this.session.cerrarSesion();
            dispose();
            new VentanaHistorial(this, this.nombreUsuario);

        });


        panelBotones.add(btnJugar);
        panelBotones.add(btnHistorial);
        panelBotones.add(btnSalir);

        add(lblBienvenida, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
    }
}