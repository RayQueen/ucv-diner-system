package controllers;

import view.ValidacionCIView;
import models.CI;
import javax.swing.*;
import java.awt.event.*;

public class ValidacionCIController implements ActionListener {
    private ValidacionCIView view;
    private CI model;

    public ValidacionCIController(ValidacionCIView view, CI model) {
        this.view = view;
        this.model = model;
        this.view.boton1.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.boton1) {
            String CI_ingresada = view.textfield1.getText().trim();
            if (CI_ingresada.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                    "Campo de cédula vacío",
                    "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            boolean credencialCorrecta = model.validarCI(CI_ingresada);
            if (credencialCorrecta) {
                JOptionPane.showMessageDialog(null,
                    "CI válida. Proceda al registro.",
                    "Acceso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                    "No estás autorizado para registrarte. Contácta a Secretaría",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
            view.textfield1.setText("");
        }
    }
}
