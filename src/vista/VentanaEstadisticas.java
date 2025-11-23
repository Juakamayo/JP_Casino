package vista;

import controlador.ResultadoController;
import modelo.Estadisticas;

import javax.swing.*;
import java.awt.*;

public class VentanaEstadisticas extends JDialog {

    private final ResultadoController controller;
    private JTextArea textAreaEstadisticas;

    public VentanaEstadisticas(JFrame owner, ResultadoController resultadoController) {

        super(owner, "Estadísticas del Jugador", true);

        this.controller = resultadoController;

        initComponents();
        cargarEstadisticas();
        setupLayout();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(450, 300);
        setResizable(false);
        setLocationRelativeTo(owner);
        setVisible(true);
    }

    private void initComponents() {
        textAreaEstadisticas = new JTextArea();
        textAreaEstadisticas.setEditable(false);

        textAreaEstadisticas.setFont(new Font("Monospaced", Font.BOLD, 14));

        textAreaEstadisticas.setBackground(new Color(240, 240, 240));
    }

    private void cargarEstadisticas() {

        Estadisticas stats = controller.calcularEstadisticas();

        textAreaEstadisticas.setText(stats.toString());
        textAreaEstadisticas.setCaretPosition(0);
    }

    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("Métricas de Juego", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));

        JScrollPane scrollPane = new JScrollPane(textAreaEstadisticas);

        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(e -> dispose());

        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelSur.add(btnVolver);

        add(titulo, BorderLayout.NORTH);

        add(scrollPane, BorderLayout.CENTER);
        add(panelSur, BorderLayout.SOUTH);
    }
}