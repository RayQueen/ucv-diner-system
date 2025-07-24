package controllers;

import view.LogInView;
import view.RegisterView;

import javax.swing.*;
import java.awt.event.*;
import java.util.regex.*;

public class RegisterController implements ActionListener {
    private RegisterView registerView;
    private models.ValidRegisters validRegisters = new models.ValidRegisters("src/models/validRegisters.txt");
    private String enteredID;
    public String lastMessage;

    public RegisterController(RegisterView registerView, String enteredID) {
        this.registerView = registerView;
        this.enteredID = enteredID;
        this.registerView.registerButton.addActionListener(this);
        this.registerView.cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerView.dispose();
                LogInView logInView = new LogInView();
                models.ValidUsers validUsersModel = new models.ValidUsers("src/models/validUsers.txt");
                new controllers.LogInController(logInView, validUsersModel);
                logInView.setVisible(true);
            }
        });
        this.registerView.firstNameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerView.lastNameField.requestFocus();
            }
        });
        this.registerView.lastNameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerView.usernameField.requestFocus();
            }
        });
        this.registerView.usernameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerView.emailField.requestFocus();
            }
        });
        this.registerView.emailField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerView.passwordField.requestFocus();
            }
        });
        this.registerView.passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerView.registerButton.doClick();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerView.registerButton) {
            if (registerView.firstNameField.getText().isEmpty() ||
                registerView.lastNameField.getText().isEmpty() ||
                registerView.emailField.getText().isEmpty() ||
                registerView.usernameField.getText().isEmpty() ||
                String.valueOf(registerView.passwordField.getPassword()).isEmpty()) {
                JOptionPane.showMessageDialog(registerView,
                    "Por favor complete todos los campos obligatorios",
                    "Error", JOptionPane.ERROR_MESSAGE);
                lastMessage = "Por favor complete todos los campos obligatorios";
                return;
            }
            
            if (!isValidName(registerView.firstNameField.getText()) || !isValidName(registerView.lastNameField.getText())) {
                JOptionPane.showMessageDialog(registerView,
                    "El nombre y el apellido deben contener solo letras",
                    "Error", JOptionPane.ERROR_MESSAGE);
                lastMessage = "El nombre y el apellido deben contener solo letras";
                return;
            }
            String correoStr = registerView.emailField.getText().trim();
            if (!isValidEmail(correoStr)) {
                JOptionPane.showMessageDialog(registerView,
                    "El correo debe terminar en @gmail.com, @ucv.ve",
                    "Error", JOptionPane.ERROR_MESSAGE);
                lastMessage = "El correo no es válido";
                return;
            }
            if (!isValidPassword(String.valueOf(registerView.passwordField.getPassword()))) {
                JOptionPane.showMessageDialog(registerView,
                    "La contraseña no es válida. Debe tener al menos 8 caracteres, incluyendo una letra mayúscula, una minúscula y un dígito.",
                    "Error", JOptionPane.ERROR_MESSAGE);
                lastMessage = "La contraseña no es válida";
                return;
            }
            // Verify if the username or email already exists
            String user = registerView.usernameField.getText().trim();
            String password = String.valueOf(registerView.passwordField.getPassword()).trim();
            String fullName = registerView.firstNameField.getText().trim() + " " + registerView.lastNameField.getText().trim();
            double balance = 0.0;
            String email = registerView.emailField.getText().trim();
            boolean admin = validRegisters.isAdmin(enteredID);
            try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("src/models/validUsers.txt"))) {
                String lineaExistente;
                while ((lineaExistente = br.readLine()) != null) {
                    String[] partes = lineaExistente.split(",");
                    if (partes.length >= 6) {
                        if (partes[0].trim().equalsIgnoreCase(user)) {
                            JOptionPane.showMessageDialog(registerView,
                                "El nombre de usuario ya está registrado",
                                "Error", JOptionPane.ERROR_MESSAGE);
                            lastMessage = "El nombre de usuario ya está registrado";
                            return;
                        }
                        if (partes[5].trim().equalsIgnoreCase(email)) {
                            JOptionPane.showMessageDialog(registerView,
                                "El correo electrónico ya está registrado",
                                "Error", JOptionPane.ERROR_MESSAGE);
                            lastMessage = "El correo electrónico ya está registrado";
                            return;
                        }
                    }
                }
            } catch (java.io.IOException ex) {
                JOptionPane.showMessageDialog(registerView,
                    "Error al verificar usuario/correo: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
                lastMessage = "Error al verificar usuario/correo";
                return;
            }

            // Save the new user
            String linea = user + "," + password + "," + fullName + "," + balance + "," + admin + "," + email + "\n";
            try (java.io.FileWriter fw = new java.io.FileWriter("src/models/validUsers.txt", true)) {
                fw.write(linea);
            } catch (java.io.IOException ex) {
                JOptionPane.showMessageDialog(registerView,
                    "Error al guardar el usuario: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
                lastMessage = "Error al guardar el usuario";
                return;
            }
            JOptionPane.showMessageDialog(registerView,
                "Registro completado exitosamente",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            lastMessage = "Registro completado exitosamente";
            registerView.dispose();
            LogInView logInView = new LogInView();
            models.ValidUsers validUsersModel = new models.ValidUsers("src/models/validUsers.txt");
            new controllers.LogInController(logInView, validUsersModel);
            logInView.setVisible(true);
        }
    }

    private boolean isValidName(String name) {
        return name.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+(\\s[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+)*");
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@(gmail.com|ucv.ve)$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        return pattern.matcher(password).matches();
    }

}
