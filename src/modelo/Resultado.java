package modelo;
import modelo.Usuario;


public class Resultado {
    public Resultado(int i, String rojo, boolean b, int i1, TipoApuesta tipoApuesta) {
        Usuario u = new Usuario("pepe", "123", "Pepe");
        Resultado r = new Resultado(7, "ROJO", true, 1200, TipoApuesta.ROJO);
        u.agregarResultado(r);
        System.out.println(u.getHistorial().size());



    };





}
