package modelo;

import java.util.Random;
import java.util.Arrays;
import java.util.List;

public class Ruleta {

    public static final int MAX_HISTORIAL = 100;
    private final List<Integer> numerosRojos = Arrays.asList(1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36);
    private final Random rng = new Random();



    public int girarRuleta() {
        return rng.nextInt(37);
    }

    public String obtenerColor(int numero) {
        if (numero == 0) {
            return "VERDE";
        }
        if (numerosRojos.contains(numero)) {
            return "ROJO";
        }
        return "NEGRO";
    }

}