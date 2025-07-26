package models;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pricing {
    private double[] rates;
    private double fixedCost;
    private double variableCost;
    private int plateNumber;
    private double shrinkage;
    private double CCB;

    public Pricing() {
        this.rates = new double[3];
        loadRates();
        loadCosts();
        calculateCCB();
    }

    public Pricing(double studentRate, double teacherRate, double employeeRate) {
        this.rates = new double[]{studentRate, teacherRate, employeeRate};
        loadCosts();
    }

    public double getRate(int index) {
        if (index < 0 || index >= 3) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        return rates[index];
    }

    public void loadRates() {
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream("src/models/data/rates.txt"), "UTF-8"))) {
            for (int i = 0; i < 3; i++) {
                String line = reader.readLine();
                if (line != null && !line.trim().isEmpty()) {
                    rates[i] = Double.parseDouble(line.trim());
                } else {
                    rates[i] = 0.0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getPricing(int index) {
        if (index < 0 || index >= 3) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        return rates[index] * CCB;
    }

    public double getFixedCost() {
        return fixedCost;
    }

    public double getVariableCost() {
        return variableCost;
    }

    public int getPlateNumber() {
        return plateNumber;
    }

    public double getShrinkage() {
        return shrinkage;
    }

    public double getCCB() {
        return CCB;
    }

    public void calculateCCB() {
        this.CCB = ((fixedCost + variableCost) / plateNumber) * (1 + shrinkage);
    }

    public void loadCosts() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/models/data/costs.txt"), "UTF-8"))) {
            String line;
            // Leer las 4 líneas (costos)
            line = reader.readLine();
            if (line != null) {
                fixedCost = Double.parseDouble(line.trim());
            }
            line = reader.readLine();
            if (line != null) {
                variableCost = Double.parseDouble(line.trim());
            }
            line = reader.readLine();
            if (line != null) {
                plateNumber = Integer.parseInt(line.trim());
            }
            line = reader.readLine();
            if (line != null) {
                shrinkage = Double.parseDouble(line.trim());
            }
            calculateCCB();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Escribe las tarifas en rates.txt
    public void updateRatesInFile(int index, double rate) {
        java.util.List<String> lines = new java.util.ArrayList<>();
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream("src/models/data/rates.txt"), "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Actualiza las primeras 3 líneas
        if (lines.size() < 3) {
            while (lines.size() < 3) lines.add("");
        }
        lines.set(index, String.valueOf(rate));
        // Escribe el archivo completo
        try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.OutputStreamWriter(new java.io.FileOutputStream("src/models/data/rates.txt"), "UTF-8"))) {
            for (int i = 0; i < 3; i++) {
                writer.write(lines.get(i));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Escribe los costos en costs.txt
    public void updateCostsInFile(double fixedCost, double variableCost, int plateNumber, double shrinkage) {
        java.util.List<String> lines = new java.util.ArrayList<>();
        // Leer el archivo actual
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream("src/models/data/costs.txt"), "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Rellenar solo los índices 0-3 si faltan
        for (int i = 0; i <= 3; i++) {
            if (lines.size() <= i) {
                lines.add("");
            }
        }
        // Actualiza SOLO las líneas de costos (índices 0-3)
        lines.set(0, String.valueOf(fixedCost));
        lines.set(1, String.valueOf(variableCost));
        lines.set(2, String.valueOf(plateNumber));
        lines.set(3, String.valueOf(shrinkage));
        // Escribe el archivo completo
        try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.OutputStreamWriter(new java.io.FileOutputStream("src/models/data/costs.txt"), "UTF-8"))) {
            for (int i = 0; i < 4; i++) {
                writer.write(lines.get(i));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
