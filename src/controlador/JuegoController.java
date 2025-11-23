package controlador;

import modelo.*;

import vista.VentanaJuego;

import javax.swing.*;
import java.awt.*;
import modelo.TipoApuesta;

public class JuegoController extends Component {

    private final Ruleta ruleta;
    private final ResultadoController resultadoController;
    private SessionController sessionController;



    public JuegoController(ResultadoController resultadoController, SessionController sessionController) {
        this.ruleta = new Ruleta();
        this.sessionController = sessionController;
        this.resultadoController = resultadoController;
    }

    public double getSaldoActual() {
        Usuario usuario = sessionController.getUsuarioActual();
        return (usuario != null) ? usuario.getSaldo() : 0.0;
    }

    public Resultado ejecutarRonda(ApuestaBase apuesta) {

        Usuario usuario = sessionController.getUsuarioActual();
        double montoApostado = apuesta.getMontoApostado();



        if (montoApostado <= 0 || montoApostado > usuario.getSaldo()) {
            throw new  IllegalArgumentException("Monto no valido");

        }

        int numeroGirado = ruleta.girarRuleta();
        String colorGirado = ruleta.obtenerColor(numeroGirado);

        boolean acierto = apuesta.acierta(numeroGirado, colorGirado);
        double montoGanado = 0;

        if (acierto) {
            montoGanado = montoApostado * 2;
            usuario.setSaldo(usuario.getSaldo() + montoGanado);

        } else {
            usuario.setSaldo(usuario.getSaldo() - montoApostado);
        }

        TipoApuesta tipoApuesta = TipoApuesta.valueOf(apuesta.getEtiqueta().toUpperCase());


        Resultado resultado = new Resultado(numeroGirado, 0, montoApostado, montoGanado, acierto, tipoApuesta);

        resultadoController.guardarResultado(resultado);
        return resultado;
    }
}