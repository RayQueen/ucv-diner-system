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
    public String ultimoMensaje;

    public CostosController(CostosView view, UsuarioRegistrado usuario) {
    this.view = view;
    this.usuario = usuario;
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
        view.PantallaInicioAdminView adminView = new view.PantallaInicioAdminView();
        adminView.actualizarUsuario(usuario.getNombreCompleto());
        new controllers.PantallaInicioAdminController(adminView, usuario);
        adminView.setVisible(true);
        adminView.setBounds(0, 0, 800, 600);
        adminView.setResizable(false);
        adminView.setLocationRelativeTo(null);
    });
}

    private void guardarCostos() {
        String costoFijoStr = view.campoCostoFijo.getText();
        String costoVariableStr = view.campoCostoVariable.getText();
        if (costoFijoStr.isEmpty() || costoVariableStr.isEmpty()) {
            ultimoMensaje = "Por favor complete todos los campos obligatorios";
            JOptionPane.showMessageDialog(view, "Por favor complete todos los campos obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            double costoFijo = Double.parseDouble(costoFijoStr);
            double costoVariable = Double.parseDouble(costoVariableStr);
            if (costoFijo < 0 || costoVariable < 0) {
                ultimoMensaje = "Los costos no pueden ser negativos.";
                JOptionPane.showMessageDialog(view, "Los costos no pueden ser negativos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try (java.io.FileWriter writer = new java.io.FileWriter("src/models/costos.txt", false)) {
                writer.write(costoFijo + "\n");
                writer.write(costoVariable + "\n");
                ultimoMensaje = "Costos guardados correctamente.";
                JOptionPane.showMessageDialog(view,
                        "Datos guardados correctamente en el archivo costos.txt",
                        "Guardado Exitoso",
                        JOptionPane.INFORMATION_MESSAGE);
                view.dispose();
                view.PantallaInicioAdminView adminView = new view.PantallaInicioAdminView();
                adminView.actualizarUsuario(usuario.getNombreCompleto());
                new controllers.PantallaInicioAdminController(adminView, usuario);
                adminView.setVisible(true);
                adminView.setBounds(0, 0, 800, 600);
                adminView.setResizable(false);
                adminView.setLocationRelativeTo(null);
            } catch (java.io.IOException ex) {
                ultimoMensaje = "Ocurrió un error al guardar el archivo.";
                JOptionPane.showMessageDialog(view,
                        "Ocurrió un error al guardar el archivo.",
                        "Error de Guardado",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            ultimoMensaje = "Ingrese valores numéricos válidos.";
            JOptionPane.showMessageDialog(view, "Ingrese valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
