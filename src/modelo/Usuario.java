package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Usuario implements Serializable {



    private String username;
    private String password;
    private String nombre;
    private double saldo;
    private final List<Resultado> historial = new ArrayList<>();

    public Usuario(String username, String password, String nombre) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
    }

    public boolean validarCredenciales(String u, String p) {
        return this.username.equals(u) && this.password.equals(p);
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsername() {
        return username;
    }



    public double getSaldo() {return saldo;}

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }


    public void agregarResultado(Resultado r) {

        historial.add(r);
    }

    public List<Resultado> getHistorial(){
        return Collections.unmodifiableList(historial);
    }

    }
