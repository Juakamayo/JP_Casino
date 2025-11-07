package modelo;

public class ApuestaVerde extends ApuestaBase {

    public ApuestaVerde(double montoApostado) {
        super(montoApostado, "VERDE");
    }

    public boolean acierta(int numeroGanador, String colorGanador) {

        return colorGanador.equals("VERDE");
    }
}