package controlador;

import modelo.Resultado;
import modelo.Usuario;
import java.util.List;
import java.util.Collections;

public class ResultadoController {

    private final SessionController sessionController;

    public ResultadoController() {

        this.sessionController = SessionController.getInstance();
    }


    public void guardarResultado(Resultado resultado) {
        Usuario usuario = sessionController.getUsuarioActual();
        if (usuario != null) {
            usuario.agregarResultado(resultado);

        }
    }
    public List<Resultado> recuperarHistorial(){
        Usuario usuario = sessionController.getUsuarioActual();
        if (usuario != null) {

            return usuario.getHistorial();
        }
        return Collections.emptyList();
    }
}