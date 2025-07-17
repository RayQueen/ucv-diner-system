package controllers;

import view.ValidacionCIView;
import models.CI;
import view.RegistroView;

import javax.swing.*;
import java.awt.event.*;

public class ValidacionCIController implements ActionListener {
    private ValidacionCIView view;
    private CI model;
    public String ultimoMensaje;

    public ValidacionCIController(ValidacionCIView view, CI model) {
        this.view = view;
        this.model = model;
        this.view.enviar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.enviar) {
            String CI_ingresada = view.cedula.getText().trim();
            if (CI_ingresada.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                    "Campo de cédula vacío",
                    "Error", JOptionPane.WARNING_MESSAGE);
                    ultimoMensaje = "Campo de cédula vacío";
                return;
            }
            boolean credencialCorrecta = model.validarCI(CI_ingresada);
            if (credencialCorrecta) {
                ultimoMensaje = "CI válida. Proceda al registro.";
                view.dispose();
                RegistroView registroView = new RegistroView();
                new RegistroController(registroView);
                registroView.setVisible(true);
                registroView.setBounds(0, 0, 500, 400);
                registroView.setVisible(true);
                registroView.setResizable(false);
                registroView.setLocationRelativeTo(null);
            } else {
                JOptionPane.showMessageDialog(null,
                    "No estás autorizado para registrarte. Contácta a Secretaría",
                    "Error", JOptionPane.ERROR_MESSAGE);
                    ultimoMensaje = "No estás autorizado para registrarte. Contácta a Secretaría";
            }
        }
    }
}
