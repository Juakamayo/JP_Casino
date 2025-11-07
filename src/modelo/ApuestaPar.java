package modelo;

public class ApuestaPar extends ApuestaBase {

    public ApuestaPar(double montoApostado) {
        super(montoApostado, "PAR");
    }


    public boolean acierta(int numeroGanador, String colorGanador) {


        return numeroGanador != 0 && numeroGanador % 2 == 0;
    }
}