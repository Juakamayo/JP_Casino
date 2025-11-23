package modelo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estadisticas implements Serializable {


    private final int totalJugadas;
    private final int victorias;
    private final double porcentajeVictorias;
    private final int rachaMaxima;
    private final TipoApuesta tipoMasJugado;

    public Estadisticas(List<Resultado> historial) {

        this.totalJugadas = historial.size();

        this.victorias = calcularVictorias(historial);
        this.porcentajeVictorias = calcularPorcentajeVictorias();

        this.rachaMaxima = calcularRachaMaxima(historial);
        this.tipoMasJugado = calcularTipoMasJugado(historial);
    }


    private int calcularVictorias(List<Resultado> historial) {

        return (int) historial.stream().filter(Resultado::getGano).count();
    }

    private double calcularPorcentajeVictorias() {
        if (totalJugadas == 0) return 0.0;

        return (Math.round(((double) victorias / totalJugadas) * 10000.0) / 100.0);
    }

    private int calcularRachaMaxima(List<Resultado> historial) {
        int rachaActual = 0;
        int maxRacha = 0;

        for (Resultado r : historial) {
            if (r.getGano()) {
                rachaActual++;
            } else {
                maxRacha = Math.max(maxRacha, rachaActual);
                rachaActual = 0;
            }
        }
        return Math.max(maxRacha, rachaActual);
    }

    private TipoApuesta calcularTipoMasJugado(List<Resultado> historial) {
        if (historial.isEmpty()) return null;

        Map<TipoApuesta, Long> conteo = new HashMap<>();
        for (Resultado r : historial) {


            TipoApuesta tipo = r.getTipoApuesta();
            conteo.put(tipo, conteo.getOrDefault(tipo, 0L) + 1);
        }

        return conteo.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public int getTotalJugadas() { return totalJugadas; }
    public int getVictorias() { return victorias; }
    public double getPorcentajeVictorias() { return porcentajeVictorias; }
    public int getRachaMaxima() { return rachaMaxima; }
    public TipoApuesta getTipoMasJugado() { return tipoMasJugado; }

    public String toString() {
        String tipoMas = (tipoMasJugado != null) ? tipoMasJugado.name() : "N/A";
        return String.format(
                "--- Estadísticas del Jugador ---\n" +
                        "Total de Jugadas: %d\n" +
                        "Victorias: %d (%.2f%%)\n" +
                        "Racha Máxima de Aciertos: %d\n" +
                        "Tipo de Apuesta Más Frecuente: %s",
                totalJugadas, victorias, porcentajeVictorias, rachaMaxima, tipoMas);
    }
}