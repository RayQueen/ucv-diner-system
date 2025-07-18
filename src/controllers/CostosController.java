package controllers;

import view.CostosView;
import view.PantallaInicioAdminView;

import javax.swing.*;

import models.UsuarioRegistrado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CostosController {
    private CostosView view;
    private UsuarioRegistrado usuario;

    public CostosController(CostosView view, UsuarioRegistrado usuario) {
    this.view = view;
    this.usuario = usuario;
    this.view.actualizarUsuario(usuario.getNombreCompleto());
    this.view.botonGuardar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            guardarCostos();
        }
    });
    this.view.botonMenu.addActionListener(e -> {
        view.panelLateral.setVisible(!view.panelLateral.isVisible());
        view.revalidate();
        view.repaint();
    });
    this.view.botonInicio.addActionListener(e -> {
        view.dispose();
        PantallaInicioAdminView inicioView = new PantallaInicioAdminView();
        new PantallaInicioAdminController(inicioView, usuario);
        inicioView.setVisible(true);
        inicioView.setBounds(0, 0, 500, 400);
        inicioView.setResizable(false);
        inicioView.setLocationRelativeTo(null);
    });
}

    private void guardarCostos() {
        String costoFijoStr = view.campoCostoFijo.getText();
        String costoVariableStr = view.campoCostoVariable.getText();
        if (costoFijoStr.isEmpty() || costoVariableStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Ambos campos deben tener un valor.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            double costoFijo = Double.parseDouble(costoFijoStr);
            double costoVariable = Double.parseDouble(costoVariableStr);
            if (costoFijo < 0 || costoVariable < 0) {
                JOptionPane.showMessageDialog(view, "Los costos no pueden ser negativos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try (java.io.FileWriter writer = new java.io.FileWriter("src/models/costos.txt", false)) {
                writer.write(costoFijo + "\n");
                writer.write(costoVariable + "\n");
                JOptionPane.showMessageDialog(view,
                        "Datos guardados correctamente en el archivo costos.txt",
                        "Guardado Exitoso",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (java.io.IOException ex) {
                JOptionPane.showMessageDialog(view,
                        "Ocurrió un error al guardar el archivo.",
                        "Error de Guardado",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Ingrese valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
