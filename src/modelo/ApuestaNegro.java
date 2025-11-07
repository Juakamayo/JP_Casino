package modelo;

public class ApuestaNegro extends ApuestaBase {

    public ApuestaNegro(double montoApostado) {
        super(montoApostado, "Negro");
    }

    public boolean acierta(int numeroGanador, String colorGanador) {

        return colorGanador.equals("Negro");
    }
}