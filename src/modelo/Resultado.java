package modelo;
import modelo.Usuario;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Resultado {

    private final int numeroGanador;
    private final int numeroApostado;
    private final double montoApostado;
    private final double montoGanado;
    private final boolean gano;


    public Resultado(int numeroGanador, int numeroApostado, double montoApostado, double montoGanado, boolean gano){
        this.numeroGanador = numeroGanador;
        this.numeroApostado = numeroApostado;
        this.montoApostado = montoApostado;
        this.montoGanado = montoGanado;
        this.gano = gano;
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

    public String toString() {
        String estado = gano ? "GANADA" : "PERDIDA";
        String gananciaStr = gano ? String.format("+ $%.2f", getMontoGanado() - getMontoApostado()) : String.format("- $%.2f", getMontoApostado());

        return String.format("%-8s | Número Ganador: %2d | Apostado: $%.2f | Resultado: %s", estado, numeroGanador, montoApostado, gananciaStr);
    }
    }


