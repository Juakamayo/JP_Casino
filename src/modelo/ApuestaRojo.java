package modelo;

public class ApuestaRojo extends ApuestaBase {

    public ApuestaRojo(double montoApostado) {
        super(montoApostado, "ROJO");
    }

    public boolean acierta(int numeroGanador, String colorGanador) {

        return colorGanador.equals("ROJO");
    }
}