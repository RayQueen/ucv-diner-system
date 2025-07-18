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
            String correoStr = view.correo.getText().trim();
            if (!correoStr.matches("^[A-Za-z0-9._%+-]+@(gmail\\.com|ucv\\.ve|ucv\\.[a-zA-Z]+\\.ve)$")) {
                JOptionPane.showMessageDialog(view,
                    "El correo debe terminar en @gmail.com, @ucv.ve o @ucv.<facultad>.ve",
                    "Error", JOptionPane.ERROR_MESSAGE);
                ultimoMensaje = "El correo debe terminar en @gmail.com, @ucv.ve o @ucv.<facultad>.ve";
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
            // Verificar que usuario y correo no existan
            String usuario = view.usuario.getText().trim();
            String contrasena = String.valueOf(view.contrasena.getPassword()).trim();
            String nombreCompleto = view.nombre.getText().trim() + " " + view.apellido.getText().trim();
            double saldo = 0.0;
            boolean admin = view.rol.getSelectedItem().toString().equalsIgnoreCase("admin");
            String correo = view.correo.getText().trim();
            try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("src/models/usuarios.txt"))) {
                String lineaExistente;
                while ((lineaExistente = br.readLine()) != null) {
                    String[] partes = lineaExistente.split(",");
                    if (partes.length >= 6) {
                        if (partes[0].trim().equalsIgnoreCase(usuario)) {
                            JOptionPane.showMessageDialog(view,
                                "El nombre de usuario ya está registrado",
                                "Error", JOptionPane.ERROR_MESSAGE);
                            ultimoMensaje = "El nombre de usuario ya está registrado";
                            return;
                        }
                        if (partes[5].trim().equalsIgnoreCase(correo)) {
                            JOptionPane.showMessageDialog(view,
                                "El correo electrónico ya está registrado",
                                "Error", JOptionPane.ERROR_MESSAGE);
                            ultimoMensaje = "El correo electrónico ya está registrado";
                            return;
                        }
                    }
                }
            } catch (java.io.IOException ex) {
                JOptionPane.showMessageDialog(view,
                    "Error al verificar usuario/correo: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
                ultimoMensaje = "Error al verificar usuario/correo";
                return;
            }
            // Guardar en usuarios.txt
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
        return name.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+(\\s[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+)*");
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
