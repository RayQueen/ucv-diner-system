package controllers;

import view.RegistroView;
import javax.swing.*;
import java.awt.event.*;
import java.util.regex.*;

public class RegistroController implements ActionListener {
    private RegistroView view;
    public String ultimoMensaje;

    public RegistroController(RegistroView view) {
        this.view = view;
        this.view.registrarse.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.registrarse) {
            if (view.nombre.getText().isEmpty() ||
                view.apellido.getText().isEmpty() ||
                view.correo.getText().isEmpty() ||
                view.telefono.getText().isEmpty() ||
                view.ocupacion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(view,
                    "Por favor complete todos los campos obligatorios",
                    "Error", JOptionPane.ERROR_MESSAGE);
                ultimoMensaje = "Por favor complete todos los campos obligatorios";
                return;
            }
            if (!isValidName(view.nombre.getText()) || !isValidName(view.apellido.getText())) {
                JOptionPane.showMessageDialog(view,
                    "El nombre y el apellido deben contener solo letras",
                    "Error", JOptionPane.ERROR_MESSAGE);
                ultimoMensaje = "El nombre y el apellido deben contener solo letras";
                return;
            }
            if (!isValidEmail(view.correo.getText())) {
                JOptionPane.showMessageDialog(view,
                    "El correo electrónico no es válido",
                    "Error", JOptionPane.ERROR_MESSAGE);
                ultimoMensaje = "El correo electrónico no es válido";
                return;
            }
            if (!isValidPhone(view.telefono.getText())) {
                JOptionPane.showMessageDialog(view,
                    "El teléfono debe contener solo números",
                    "Error", JOptionPane.ERROR_MESSAGE);
                ultimoMensaje = "El teléfono debe contener solo números";
                return;
            }
            JOptionPane.showMessageDialog(view,
                "Registro completado exitosamente",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            ultimoMensaje = "Registro completado exitosamente";
            view.nombre.setText("");
            view.apellido.setText("");
            view.correo.setText("");
            view.telefono.setText("");
            view.ocupacion.setText("");
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
