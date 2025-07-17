package models;

import java.io.*;

public class Usuario {
    private String[][] usuariosValidos;

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
            usuariosValidos = new String[totalUsuarios][2];
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
            String linea;
            int index = 0;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    usuariosValidos[index][0] = partes[0].trim();
                    usuariosValidos[index][1] = partes[1].trim();
                    index++;
                }
            }
            br.close();
        } catch (IOException e) {
            // Manejo de error
        }
    }

    public boolean validarUsuario(String usuario, String contrasena) {
        for (int i = 0; i < usuariosValidos.length; i++) {
            if (usuariosValidos[i][0].equals(usuario)
                && usuariosValidos[i][1].equals(contrasena)) {
                return true;
            }
        }
        return false;
    }
}
