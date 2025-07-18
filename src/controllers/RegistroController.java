package controllers;

import view.IniciarSesionView;
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
                view.usuario.getText().isEmpty() ||
                String.valueOf(view.contrasena.getPassword()).isEmpty()) {
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
            // Guardar en usuarios.txt
            String usuario = view.usuario.getText().trim();
            String contrasena = String.valueOf(view.contrasena.getPassword()).trim();
            String nombreCompleto = view.nombre.getText().trim() + " " + view.apellido.getText().trim();
            double saldo = 0.0;
            boolean admin = view.rol.getSelectedItem().toString().equalsIgnoreCase("admin");
            String correo = view.correo.getText().trim();
            // Formato: usuario,contraseña,nombreCompleto,saldo,admin,correo
            String linea = usuario + "," + contrasena + "," + nombreCompleto + "," + saldo + "," + admin + "," + correo + "\n";
            try (java.io.FileWriter fw = new java.io.FileWriter("src/models/usuarios.txt", true)) {
                fw.write(linea);
            } catch (java.io.IOException ex) {
                JOptionPane.showMessageDialog(view,
                    "Error al guardar el usuario: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
                ultimoMensaje = "Error al guardar el usuario";
                return;
            }
            JOptionPane.showMessageDialog(view,
                "Registro completado exitosamente",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            ultimoMensaje = "Registro completado exitosamente";
            view.dispose();
            IniciarSesionView iniciarSesionView = new IniciarSesionView();
            models.Usuario usuarioModel = new models.Usuario("src/models/usuarios.txt");
            new controllers.IniciarSesionController(iniciarSesionView, usuarioModel);
            iniciarSesionView.setBounds(0, 0, 500, 400);
            iniciarSesionView.setVisible(true);
            iniciarSesionView.setResizable(false);
            iniciarSesionView.setLocationRelativeTo(null);
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
