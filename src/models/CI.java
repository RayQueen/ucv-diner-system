package models;

import java.io.*;

public class CI {
    private String[] CIValidas;

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
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
            String linea;
            int index = 0;
            while ((linea = br.readLine()) != null) {
                CIValidas[index] = linea.trim();
                index++;
            }
            br.close();
        } catch (IOException e) {
            // Manejo de error
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
}
