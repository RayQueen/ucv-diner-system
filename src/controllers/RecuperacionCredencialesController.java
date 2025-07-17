package controllers;

import view.RecuperacionCredencialesView;
import javax.swing.*;
import java.awt.event.*;
import java.util.regex.*;

public class RecuperacionCredencialesController implements ActionListener {
    private RecuperacionCredencialesView view;
    public String ultimoMensaje;

    public RecuperacionCredencialesController(RecuperacionCredencialesView view) {
        this.view = view;
        this.view.enviar.addActionListener(this);
        this.view.correo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.enviar.doClick();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.enviar) {
            String correo = view.correo.getText().trim();
            if (correo.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                    "Ingrese su correo",
                    "Error", JOptionPane.WARNING_MESSAGE);
                    ultimoMensaje = "Ingrese su correo";
                return;
            }
            if (!isValidEmail(correo)) {
                JOptionPane.showMessageDialog(view,
                    "El correo electrónico no es válido",
                    "Error", JOptionPane.ERROR_MESSAGE);
                    ultimoMensaje = "El correo electrónico no es válido";
                return;
            }
            JOptionPane.showMessageDialog(view,
                "Se ha enviado un correo para restablecer su contraseña",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
                ultimoMensaje = "Se ha enviado un correo para restablecer su contraseña";
                view.dispose();
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}
