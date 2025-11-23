package controlador;

import modelo.Estadisticas;
import modelo.Resultado;
import modelo.Usuario;
import java.util.List;
import java.util.Collections;

public class ResultadoController {

    private final SessionController sessionController;


    public ResultadoController(SessionController sessionController) {
        this.sessionController = sessionController;
    }


    public void guardarResultado(Resultado resultado) {
        Usuario usuario = sessionController.getUsuarioActual();
        if (usuario != null) {
            usuario.agregarResultado(resultado);
        }
    }

    public Estadisticas calcularEstadisticas(){
        List<Resultado> historial = recuperarHistorial();
        return new Estadisticas(historial);
    }

    public List<Resultado> recuperarHistorial(){
        Usuario usuario = sessionController.getUsuarioActual();
        if (usuario != null) {
            return usuario.getHistorial();
        }
        return Collections.emptyList();
    }
}