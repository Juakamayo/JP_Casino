package controlador;

import modelo.Ruleta;
import modelo.TipoApuesta;
import modelo.Resultado; // Importar la clase Resultado

public class JuegoController {

    private final Ruleta ruleta;
    private final ResultadoController resultadoController; // Necesitamos este para guardar el historial
    private int saldoActual;

    public JuegoController(int saldoInicial, ResultadoController resultadoController) {
        this.ruleta = new Ruleta();
        this.saldoActual = saldoInicial;
        this.resultadoController = resultadoController; // Asignar el resultadoController
    }

    public int getSaldoActual() {
        return saldoActual;
    }


    public Resultado ejecutarRonda(int montoApostado, TipoApuesta tipoApuestaEnum) {
        if (montoApostado <= 0 || montoApostado > saldoActual) {
            // Esto debería ser validado en la UI, pero lo incluimos por seguridad
            throw new IllegalArgumentException("Monto inválido o insuficiente.");
        }

        int numeroGirado = ruleta.girarRuleta();
        boolean acierto = ruleta.evaluarResultado(numeroGirado, tipoApuestaEnum);
        double montoGanado = 0;

        if (acierto) {
            montoGanado = montoApostado * 2; // Recuperas lo apostado + monto igual
            saldoActual += montoApostado; // Solo se suma el beneficio, ya que el monto apostado "volvió"
        } else {
            saldoActual -= montoApostado;
            montoGanado = 0;
        }

        // Crear el objeto Resultado
        Resultado resultado = new Resultado(
                numeroGirado,
                0, // Aquí iría el número apostado si fuera una apuesta numérica directa
                montoApostado,
                montoGanado,
                acierto
        );

        // Guardar el resultado en el historial del usuario a través del ResultadoController
        resultadoController.guardarResultado(resultado);

        return resultado; // Devolver el resultado de la ronda para que la UI lo muestre
    }
}