package controllers;

import view.RecuperacionCredencialesView;
import javax.swing.*;
import java.awt.event.*;
import java.util.regex.*;

public class RecuperacionCredencialesController implements ActionListener {
    private RecuperacionCredencialesView view;

    public RecuperacionCredencialesController(RecuperacionCredencialesView view) {
        this.view = view;
        this.view.boton1.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.boton1) {
            String correo = view.textfield1.getText().trim();
            if (correo.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                    "Ingrese su correo",
                    "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!isValidEmail(correo)) {
                JOptionPane.showMessageDialog(view,
                    "El correo electrónico no es válido",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(view,
                "Se ha enviado un correo para restablecer su contraseña",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            view.textfield1.setText("");
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}
