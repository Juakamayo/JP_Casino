package controlador;

import modelo.Ruleta;

public class RuletaController {
    private final SessionController session;

    public RuletaController(Ruleta ruleta, SessionController session) {
        this.session = session;
    }
}
