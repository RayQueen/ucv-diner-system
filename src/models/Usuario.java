package models;

import java.io.*;

public class Usuario {
    public UsuarioRegistrado[] usuariosValidos;

    public Usuario(String rutaArchivo) {
        cargarUsuarios(rutaArchivo);
    }

    private void cargarUsuarios(String rutaArchivo) {
        try {
            BufferedReader brCount = new BufferedReader(new FileReader(rutaArchivo));
            int totalUsuarios = 0;
            while (brCount.readLine() != null) {
                totalUsuarios++;
            }
            brCount.close();
            usuariosValidos = new UsuarioRegistrado[totalUsuarios];
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/models/usuarios.txt"), "UTF-8"));
            String linea;
            int index = 0;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 5) {
                    String usuario = partes[0].trim();
                    String contrasena = partes[1].trim();
                    String nombreCompleto = partes[2].trim();
                    double saldo = Double.parseDouble(partes[3].trim());
                    boolean admin = Boolean.parseBoolean(partes[4].trim());
                    String correo = partes[5].trim();
                    usuariosValidos[index] = new UsuarioRegistrado(usuario, contrasena, nombreCompleto, saldo, admin, correo);
                    index++;
                }
            }
            reader.close();
        } catch (IOException e) {
            // Manejo de error
        }
    }

    public boolean validarUsuario(String usuario, String contrasena) {
        for (int i = 0; i < usuariosValidos.length; i++) {
            if (usuariosValidos[i].getUsuario().equals(usuario)
                && usuariosValidos[i].getContrasena().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }

    public UsuarioRegistrado getUsuario(String usuario) {
        for (UsuarioRegistrado usuarioRegistrado : usuariosValidos) {
            if (usuarioRegistrado.getUsuario().equals(usuario)) {
                return usuarioRegistrado;
            }
        }
        return null; // Usuario no encontrado
    }
}
