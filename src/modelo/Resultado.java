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
    }


