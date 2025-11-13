package controlador;

import modelo.Resultado;
import modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

import java.io.*;
import java.util.Scanner;

public class SessionController {

    private static final String FILE_NAME = "usuarios.csv";

    private List<Usuario> usuarios = new ArrayList<>();
    private Usuario usuarioActual;


    public SessionController() {
        this.usuarios = new ArrayList<>();
        cargarUsuarios();

        if (usuarios.isEmpty()) {
            usuarios.add(new Usuario("Juakamayo", "1234", "Juakamayo"));
            usuarios.add(new Usuario("admin", "admin", "Administrador"));
        }
    }

    private void cargarUsuarios() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            this.usuarios = new ArrayList<>();
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length == 3) {
                    try {
                        String username = parts[0];
                        String password = parts[1];
                        double saldo = Double.parseDouble(parts[2]);


                        Usuario usuario = new Usuario(username, password, username);
                        this.usuarios.add(usuario);

                    } catch (NumberFormatException e) {
                        System.err.println("Error al parsear el saldo en el CSV: " + line);
                    }
                } else {
                    System.err.println("Línea mal formateada en el CSV: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Archivo CSV de usuarios no encontrado: " + e.getMessage());
            this.usuarios = new ArrayList<>();
        }
    }

    public void guardarUsuarios() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Usuario usuario : usuarios) {

                String line = String.format(java.util.Locale.US, "%s,%s,%.2f",
                        usuario.getUsername(),
                        usuario.getPassword(),
                        usuario.getSaldo());
                writer.println(line);
            }
            System.out.println("Usuarios guardados exitosamente en " + FILE_NAME);

        } catch (IOException e) {
            System.err.println("Error al guardar usuarios en CSV: " + e.getMessage());
        }
    }


    private void cargarHistorialUsuario(Usuario usuario) {
        String fileName = usuario.getUsername() + "_historial.dat";
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {

            List<Resultado> historialCargado = (List<Resultado>) ois.readObject();
            usuario.setHistorial(historialCargado);
            System.out.println("Historial de " + usuario.getUsername() + " cargado exitosamente.");
        } catch (FileNotFoundException e) {

            System.out.println("Historial no encontrado para " + usuario.getUsername() + ". Se usara historial vacío.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar historial para " + usuario.getUsername() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void guardarHistorialUsuario(Usuario usuario) {
        if (usuario == null) return;
        String fileName = usuario.getUsername() + "_historial.dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(usuario.getHistorial());
            System.out.println("Historial de " + usuario.getUsername() + " guardado exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar historial para " + usuario.getUsername() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void guardarDatosSesion() {

        guardarUsuarios();

        if (usuarioActual != null) {
            guardarHistorialUsuario(usuarioActual);
        }
    }


    public Usuario getUsuarioActual() {
        return usuarioActual;
    }




    public Usuario iniciarSesion(String u, String p) {
        for (Usuario usuario : usuarios) {
            if (usuario.validarCredenciales(u, p)) {
                this.usuarioActual = usuario;
                cargarHistorialUsuario(usuarioActual);
                return usuario;
            }
        }
        return null;
    }


    public void registrarUsuario(String u, String p, String n) {
        Usuario nuevoUsuario = new Usuario(u, p, n);
        usuarios.add(nuevoUsuario);
    }

    public String getNombreUsuario() {
        return hayUsuario() ? usuarioActual.getNombre() : "";
    }

    public boolean hayUsuario() {
        return usuarioActual != null;
    }

    public void cerrarSesion() {
        if (usuarioActual != null) {
            guardarHistorialUsuario(usuarioActual);
            usuarioActual = null;
        }
    }
}