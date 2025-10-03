package vista;

import controlador.SessionController;
import modelo.Usuario;
import javax.swing.*;
import java.awt.*;

public class VentanaRegistro extends JDialog {

    private JTextField txtUsuario;
    private JPasswordField txtClave;
    private JTextField txtNombre;
    private JButton btnRegistrar;

    public VentanaRegistro(JFrame owner) {
        super(owner, "Registro de Nuevo Usuario", true);

        initComponents();
        setupLayout();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(owner);
        setVisible(true);
    }

    private void initComponents() {
        txtUsuario = new JTextField(15);
        txtClave = new JPasswordField(15);
        txtNombre = new JTextField(15);
        btnRegistrar = new JButton("Completar Registro");

        // Asignamos la lógica al botón de registro
        btnRegistrar.addActionListener(e -> registrar());
    }

    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));

        // Panel para los campos (Usuario, Clave, Nombre)
        JPanel panelForm = new JPanel(new GridLayout(3, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        panelForm.add(new JLabel("Nombre de Usuario:"));
        panelForm.add(txtUsuario);
        panelForm.add(new JLabel("Clave:"));
        panelForm.add(txtClave);
        panelForm.add(new JLabel("Nombre Completo:"));
        panelForm.add(txtNombre);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.add(btnRegistrar);

        add(panelForm, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }


    private void registrar() {
        String user = txtUsuario.getText().trim();
        String pass = new String(txtClave.getPassword());
        String name = txtNombre.getText().trim();


        if (user.isEmpty() || pass.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Todos los campos son obligatorios.",
                    "Error de Validación",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }


        SessionController controller = SessionController.getInstance();


        controller.registrarUsuario(user, pass, name);


        JOptionPane.showMessageDialog(this,
                "¡Registro exitoso! Ya puedes iniciar sesión.",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);

        dispose();
    }
}