package modelo;
import java.io.Serializable;


public class Resultado implements Serializable {



    private final int numeroGanador;
    private final int numeroApostado;
    private final double montoApostado;
    private final double montoGanado;
    private final boolean gano;
    private final TipoApuesta tipoApuesta;


    public Resultado(int numeroGanador, int numeroApostado, double montoApostado, double montoGanado, boolean gano, TipoApuesta tipoApuesta) {
        this.numeroGanador = numeroGanador;
        this.numeroApostado = numeroApostado;
        this.montoApostado = montoApostado;
        this.montoGanado = montoGanado;
        this.gano = gano;
        this.tipoApuesta = tipoApuesta;
    }

    public TipoApuesta getTipoApuesta() {
        return tipoApuesta;
    }




    public int getNumeroGanador(){
        return numeroGanador;
    }
    public int getNumeroApostado(){
        return numeroApostado;
    }
    public double getMontoApostado(){
        return montoApostado;
    }
    public double getMontoGanado(){
        return montoGanado;
    }
    public boolean getGano(){
        return gano;
    }
    public TipoApuesta getTipoapuesta(){return tipoApuesta;}

    public String toString() {
        String estado = getGano() ? "GANADA" : "PERDIDA";

        String gananciaStr = getGano()
                ? String.format("+ $%.2f", getMontoGanado() - getMontoApostado())
                : String.format("- $%.2f", getMontoApostado());

        return String.format("%-8s | Apuesta: %-5s | Ganador: %2d | Apostado: $%.2f | Resultado: %s",
                estado, getTipoApuesta().name(), getNumeroGanador(), getMontoApostado(), gananciaStr);
    }
    }


