package controlador;

import modelo.Ruleta;

import modelo.Resultado;
import modelo.ApuestaBase;

public class JuegoController {

    private final Ruleta ruleta;
    private final ResultadoController resultadoController; // Necesitamos este para guardar el historial
    private int saldoActual;

    public JuegoController(int saldoInicial, ResultadoController resultadoController) {
        this.ruleta = new Ruleta();
        this.saldoActual = saldoInicial;
        this.resultadoController = resultadoController; // Asignar el resultadoController
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public Resultado ejecutarRonda(ApuestaBase apuesta) {
        double montoApostado = apuesta.getMontoApostado();



        if (montoApostado <= 0 || montoApostado > saldoActual) {
            throw new  IllegalArgumentException("Monto no valido");
        }

        int numeroGirado = ruleta.girarRuleta();
        String colorGirado = ruleta.obtenerColor(numeroGirado);

        boolean acierto = apuesta.acierta(numeroGirado, colorGirado);
        double montoGanado = 0;

        if (acierto) {
            montoGanado = montoApostado;
            montoGanado += montoApostado;
        } else {
            saldoActual -= montoApostado;
            montoGanado = 0;
        }


        Resultado resultado = new Resultado(numeroGirado, 0, montoApostado, montoGanado, acierto);

        resultadoController.guardarResultado(resultado);
        return resultado;
    }
}