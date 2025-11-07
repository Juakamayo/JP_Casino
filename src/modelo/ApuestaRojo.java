package modelo;

public class ApuestaRojo extends ApuestaBase {

    public ApuestaRojo(double montoApostado) {
        super(montoApostado, "Rojo");
    }

    public boolean acierta(int numeroGanador, String colorGanador) {

        return colorGanador.equals("Rojo");
    }
}