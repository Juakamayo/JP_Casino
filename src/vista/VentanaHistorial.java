package vista;


import controlador.ResultadoController;
import modelo.Resultado;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VentanaHistorial extends JDialog {

    private final ResultadoController controller = new ResultadoController();
    private JList<Resultado> listaHistorial;
    private DefaultListModel<Resultado> listModel;


    public VentanaHistorial(JFrame owner, String nombreUsuario) {
        super(owner, "Historial de " + nombreUsuario, true);

        initComponents();
        cargarHistorial();
        setupLayout();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(owner);
        setVisible(true);
    }

    private void initComponents() {
        listModel = new DefaultListModel<>();
        listaHistorial = new JList<>(listModel);


        listaHistorial.setFont(new Font("Monospaced", Font.PLAIN, 12));
    }

    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("Tus Últimas Jugadas:", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));

        JScrollPane scrollPane = new JScrollPane(listaHistorial);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> volverMenu());

        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelSur.add(btnCerrar);

        add(titulo, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelSur, BorderLayout.SOUTH);
    }
    private void volverMenu() {}



    private void cargarHistorial() {
        List<Resultado> historial = controller.recuperarHistorial();
        listModel.clear();

        if (historial.isEmpty()) {
            listModel.addElement(new Resultado(0, 0, 0, 0, false));

            listModel.set(0, new Resultado(0, 0, 0, 0, false) {
                public String toString() { return "Aún no tienes jugadas registradas."; }
            });
            return;
        }


        List<Resultado> historialReverso = new ArrayList<>(historial);
        Collections.reverse(historialReverso);

        for (Resultado r : historialReverso) {
            listModel.addElement(r);
        }
    }
}