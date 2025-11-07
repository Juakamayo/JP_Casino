package vista;

import controlador.JuegoController;
import controlador.ResultadoController;
import controlador.SessionController;
import modelo.Resultado;
import modelo.Ruleta;
import modelo.TipoApuesta;
import javax.swing.*;
import java.awt.*;
import modelo.Resultado;

public class VentanaJuego extends JFrame {

    private final SessionController sessionController;
    private final ResultadoController resultadoController;
    private final JuegoController juegoController;


    private final Ruleta ruleta;
    private final String nombreJugador;

    private JTextField txtMonto;
    private JComboBox<String> cmbTipoApuesta;
    private JLabel lblSaldo;
    private JLabel lblResultado;
    private JButton btnGirar;
    private JButton btnVolver;

    public VentanaJuego(String nombreJugador, SessionController sessionController, ResultadoController resultadoController) {
        super("Ruleta - Casino Black Cat");
        this.nombreJugador = nombreJugador;
        this.ruleta = new Ruleta();

        this.sessionController = sessionController;
        this.resultadoController = resultadoController;

        this.juegoController = new JuegoController(1000, resultadoController);

        initComponents();
        setupLayout();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        txtMonto = new JTextField(10);
        cmbTipoApuesta = new JComboBox<>(new String[]{"Rojo", "Negro", "Par", "Impar"});
        lblSaldo = new JLabel("Saldo: $" + juegoController.getSaldoActual(), SwingConstants.CENTER);
        lblResultado = new JLabel("Listo para jugar!", SwingConstants.CENTER);
        btnGirar = new JButton("Girar");
        btnGirar.addActionListener(e -> jugarRonda());
        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> volverMenu());
    }

    private void setupLayout() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel panelInputs = new JPanel(new GridLayout(2, 2, 5, 5));
        panelInputs.add(new JLabel("Tipo de apuesta:"));
        panelInputs.add(cmbTipoApuesta);
        panelInputs.add(new JLabel("Monto:"));
        panelInputs.add(txtMonto);

        JPanel panelControles = new JPanel(new BorderLayout(5, 5));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panelBotones.add(btnGirar);
        panelBotones.add(btnVolver);

        JPanel panelResultados = new JPanel(new GridLayout(2, 1, 5, 5));
        panelResultados.add(lblSaldo);
        panelResultados.add(lblResultado);

        panelControles.add(panelBotones, BorderLayout.NORTH);
        panelControles.add(panelResultados, BorderLayout.CENTER);

        panelPrincipal.add(panelInputs, BorderLayout.NORTH);
        panelPrincipal.add(panelControles, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    private void volverMenu() {
        dispose();
        new VentanaMenu(sessionController, resultadoController, nombreJugador);

    }

    private void jugarRonda() {

        int monto = 0;
        String tipoApuestaString = "";
        TipoApuesta tipoApuestaEnum = null;
        double montoGanado = 0;

        try {
            monto = Integer.parseInt(txtMonto.getText());
            tipoApuestaString = (String) cmbTipoApuesta.getSelectedItem();
            tipoApuestaEnum = TipoApuesta.valueOf(tipoApuestaString.toUpperCase());

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this, "Por favor, ingresa un monto válido (solo números).");
            return;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Error en el tipo de apuesta seleccionada.");
            return;
        }
        Resultado resultadoRonda = juegoController.ejecutarRonda(monto, tipoApuestaEnum);

        lblSaldo.setText("Saldo; $" + juegoController.getSaldoActual());
        String mensajeResultado = resultadoRonda.getGano() ?
                "GANASTE numero: " + resultadoRonda.getNumeroGanador() + ", Saldo: $" + juegoController.getSaldoActual():
                "PERDISTE numero: " + resultadoRonda.getNumeroGanador() + ", Saldo: $" + juegoController.getSaldoActual();
    }
}