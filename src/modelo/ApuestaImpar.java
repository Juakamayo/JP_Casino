package modelo;

public class ApuestaImpar extends ApuestaBase {

    public ApuestaImpar(double montoApostado) {
        super(montoApostado, "Impar");
    }


    public boolean acierta(int numeroGanador, String colorGanador) {



        return numeroGanador % 2 != 0;
    }
}