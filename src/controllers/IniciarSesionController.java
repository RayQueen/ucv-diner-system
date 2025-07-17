package controllers;

import view.IniciarSesionView;
import models.Usuario;
import models.CI;
import view.RecuperacionCredencialesView;
import view.RegistroView;
import view.ValidacionCIView;

import javax.swing.*;
import java.awt.event.*;

public class IniciarSesionController implements ActionListener {
    private IniciarSesionView view;
    private Usuario userModel = new Usuario("src/models/usuarios.txt");
    private CI identificationModel = new CI("src/models/CI_validas.txt");
    public String ultimoMensaje;

    public IniciarSesionController(IniciarSesionView view, Usuario userModel) {
        this.view = view;
        this.userModel = userModel;
        this.view.boton1.addActionListener(this);
        this.view.forgotPassword.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                RecuperacionCredencialesView view = new RecuperacionCredencialesView();
                new RecuperacionCredencialesController(view);
                view.setVisible(true);
                view.setBounds(0, 0, 500, 400);
                view.setVisible(true);
                view.setResizable(false);
                view.setLocationRelativeTo(null);
            }
        });
        this.view.usuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.contrasena.requestFocus();
            }
        });
        this.view.contrasena.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.boton1.doClick();
            }
        });
        this.view.register.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ValidacionCIView view = new ValidacionCIView();
                new ValidacionCIController(view, identificationModel);
                view.setVisible(true);
                view.setBounds(0, 0, 500, 400);
                view.setVisible(true);
                view.setResizable(false);
                view.setLocationRelativeTo(null);                
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
