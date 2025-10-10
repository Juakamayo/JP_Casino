package vista;

import controlador.ResultadoController;
import controlador.SessionController;
import modelo.Usuario;
import javax.swing.*;
import java.awt.*;

public class VentanaLogin extends JFrame {

    private final SessionController sessionController;

    private JTextField txtUsuario;
    private JPasswordField txtClave;
    private JButton btnIngresar;
    private JButton btnRegistrar;


    public VentanaLogin(SessionController controller) {
        super("Login - Casino Black Cat");

        this.sessionController = controller;

        initComponents();
        setupLayout();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        txtUsuario = new JTextField(15);
        txtClave = new JPasswordField(15);
        btnIngresar = new JButton("Ingresar");
        btnRegistrar = new JButton("Registrar");

        btnIngresar.addActionListener(e -> login());

        btnRegistrar.addActionListener(e -> new VentanaRegistro(this, sessionController));
    }

    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelForm = new JPanel(new GridLayout(2, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        panelForm.add(new JLabel("Usuario:"));
        panelForm.add(txtUsuario);
        panelForm.add(new JLabel("Clave:"));
        panelForm.add(txtClave);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnIngresar);

        add(panelForm, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void login() {
        String user = txtUsuario.getText();
        String pass = new String(txtClave.getPassword());

        Usuario usuarioLogeado = this.sessionController.iniciarSesion(user, pass);

        if (usuarioLogeado != null) {
            JOptionPane.showMessageDialog(this, "Bienvenido, " + usuarioLogeado.getNombre() + "!");

            ResultadoController resultadoController = new ResultadoController(sessionController);

            new VentanaMenu(sessionController, resultadoController, usuarioLogeado.getNombre());
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}