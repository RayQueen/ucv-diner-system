package controllers;

import view.IniciarSesionView;
import models.Usuario;
import view.RecuperacionCredencialesView;
import javax.swing.*;
import java.awt.event.*;

public class IniciarSesionController implements ActionListener {
    private IniciarSesionView view;
    private Usuario userModel = new Usuario("src/models/usuarios.txt");
    public String ultimoMensaje;

    public IniciarSesionController(IniciarSesionView view, Usuario userModel) {
        this.view = view;
        this.userModel = userModel;
        this.view.boton1.addActionListener(this);
        this.view.forgotPassword.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                RecuperacionCredencialesView ventana = new RecuperacionCredencialesView();
                ventana.setBounds(0, 0, 500, 400);
                ventana.setVisible(true);
                ventana.setResizable(false);
                ventana.setLocationRelativeTo(null);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.boton1) {
            String usuarioIngresado = view.usuario.getText().trim();
            String contrasenaIngresada = new String(view.contrasena.getPassword()).trim();

            if (usuarioIngresado.isEmpty() || contrasenaIngresada.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                    "Usuario y contraseña son obligatorios",
                    "Error", JOptionPane.WARNING_MESSAGE);
                    ultimoMensaje = "Usuario y contraseña son obligatorios";
                return;
            }

            boolean credencialCorrecta = userModel.validarUsuario(usuarioIngresado, contrasenaIngresada);
            if (credencialCorrecta) {
                JOptionPane.showMessageDialog(null,
                    "¡Bienvenido, " + usuarioIngresado + "!",
                    "Acceso concedido", JOptionPane.INFORMATION_MESSAGE);
                    ultimoMensaje = "¡Bienvenido, " + usuarioIngresado + "!";
            } else {
                JOptionPane.showMessageDialog(null,
                    "Usuario o contraseña incorrectos",
                    "Error", JOptionPane.ERROR_MESSAGE);
                    ultimoMensaje = "Usuario o contraseña incorrectos";
            }
        }
    }
}
