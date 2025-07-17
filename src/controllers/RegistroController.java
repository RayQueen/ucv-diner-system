package controllers;

import view.RegistroView;
import javax.swing.*;
import java.awt.event.*;
import java.util.regex.*;

public class RegistroController implements ActionListener {
    private RegistroView view;

    public RegistroController(RegistroView view) {
        this.view = view;
        this.view.boton1.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.boton1) {
            if (view.textfield1.getText().isEmpty() ||
                view.textfield2.getText().isEmpty() ||
                view.textfield3.getText().isEmpty() ||
                view.textfield4.getText().isEmpty() ||
                view.textfield5.getText().isEmpty() ||
                view.textfield6.getText().isEmpty()) {
                JOptionPane.showMessageDialog(view,
                    "Por favor complete todos los campos obligatorios",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!isValidName(view.textfield1.getText()) || !isValidName(view.textfield2.getText())) {
                JOptionPane.showMessageDialog(view,
                    "El nombre y el apellido deben contener solo letras",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!isValidEmail(view.textfield3.getText())) {
                JOptionPane.showMessageDialog(view,
                    "El correo electrónico no es válido",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!isValidPhone(view.textfield4.getText())) {
                JOptionPane.showMessageDialog(view,
                    "El teléfono debe contener solo números",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(view,
                "Registro completado exitosamente",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            view.textfield1.setText("");
            view.textfield2.setText("");
            view.textfield3.setText("");
            view.textfield4.setText("");
            view.textfield5.setText("");
            view.textfield6.setText("");
        }
    }

    private boolean isValidName(String name) {
        return name.matches("[a-zA-Z]+");
    }
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
    private boolean isValidPhone(String phone) {
        return phone.matches("\\d+");
    }
}
