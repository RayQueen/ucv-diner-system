package models;

import java.io.*;

public class CI {
    private String[] CIValidas;
    private boolean[] esAdmin;

    public CI(String rutaArchivo) {
        cargarCI(rutaArchivo);
    }

    private void cargarCI(String rutaArchivo) {
        try {
            BufferedReader brCount = new BufferedReader(new FileReader(rutaArchivo));
            int totalUsuarios = 0;
            while (brCount.readLine() != null) {
                totalUsuarios++;
            }
            brCount.close();
            CIValidas = new String[totalUsuarios];
            esAdmin = new boolean[totalUsuarios];
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
            String linea;
            int index = 0;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.trim().split(",");
                CIValidas[index] = partes[0].trim();
                if (partes[1].trim().equalsIgnoreCase("true")) {
                    esAdmin[index] = true;
                } else {
                    esAdmin[index] = false;
                }
                index++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean validarCI(String ci) {
        for (String civalida : CIValidas) {
            if (civalida.equals(ci)) {
                return true;
            }
        }
        return false;
    }

    public boolean esAdmin(String ci) {
        for (int i = 0; i < CIValidas.length; i++) {
            if (CIValidas[i].equals(ci)) {
                return esAdmin[i];
            }
        }
        return false;
    }
}
