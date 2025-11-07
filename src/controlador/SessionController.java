package controlador;

import modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

import java.io.*;

public class SessionController {

    private static final String USUARIOS = "usuarios.dat";

    private List<Usuario> usuarios = new ArrayList<>();
    private Usuario usuarioActual;


    public SessionController() {

        cargarUsuario();

        if (usuarios.isEmpty()) {
            usuarios.add(new Usuario("Juakamayo", "1234", "Juakamayo"));
            usuarios.add(new Usuario("admin", "admin", "Administrador"));
        }
    }

    private void cargarUsuario() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USUARIOS))) {

            usuarios = (List<Usuario>) ois.readObject();
            System.out.println("Usuarios cargados exitosamente desde " + USUARIOS);
        } catch (FileNotFoundException e) {

            System.out.println("Archivo de usuarios no encontrado. Se creará uno nuevo al guardar.");
        } catch (IOException | ClassNotFoundException e) {

            e.printStackTrace();
            System.err.println("Error al cargar los usuarios. Usando lista vacía.");
            usuarios = new ArrayList<>();
        }
    }

    public void guardarUsuarios() {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USUARIOS))) {

            oos.writeObject(usuarios);
            System.out.println("Usuarios guardados exitosamente en " + USUARIOS);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al guardar los usuarios.");
        }
    }



    public Usuario iniciarSesion(String u, String p) {
        for (Usuario usuario : usuarios) {
            if (usuario.validarCredenciales(u, p)) {
                this.usuarioActual = usuario;
                return usuario;
            }
        }
        return null;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
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
        usuarioActual = null;
    }
}