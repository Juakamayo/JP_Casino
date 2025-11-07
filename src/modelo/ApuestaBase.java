package modelo;

import java.io.Serializable;

public abstract class ApuestaBase implements Serializable {


    protected final double montoApostado;
    protected final String etiqueta;

    public ApuestaBase(double montoApostado, String etiqueta) {
        this.montoApostado = montoApostado;
        this.etiqueta = etiqueta;
    }

    public double getMontoApostado() {
        return montoApostado;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public abstract boolean acierta(int numeroGanador, String colorGanador);
}