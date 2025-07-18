package controllers;

import view.IniciarSesionView;
import models.Usuario;
import models.UsuarioRegistrado;
import models.CI;
import view.RecuperacionCredencialesView;
import view.ValidacionCIView;
import view.PantallaInicioView;

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
                view.dispose();
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
                view.dispose();
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
                UsuarioRegistrado usuario = userModel.getUsuario(usuarioIngresado);
                JOptionPane.showMessageDialog(null,
                    "¡Bienvenido, " + usuario.getNombreCompleto() + "!",
                    "Acceso concedido", JOptionPane.INFORMATION_MESSAGE);
                ultimoMensaje = "¡Bienvenido/a, " + usuario.getUsuario() + "!";
                view.dispose();
                if (usuario.esAdmin()) {
                    view.PantallaInicioAdminView adminView = new view.PantallaInicioAdminView();
                    adminView.actualizarUsuario(usuario.getNombreCompleto());
                    new controllers.PantallaInicioAdminController(adminView);
                    adminView.setVisible(true);
                    adminView.setBounds(0, 0, 800, 600);
                    adminView.setResizable(false);
                    adminView.setLocationRelativeTo(null);
                } else {
                    PantallaInicioView userView = new PantallaInicioView();
                    new PantallaInicioController(userView, usuario);
                    userView.setVisible(true);
                    userView.setBounds(0, 0, 500, 400);
                    userView.setResizable(false);
                    userView.setLocationRelativeTo(null);
                }
            } else {
                JOptionPane.showMessageDialog(null,
                    "Usuario o contraseña incorrectos",
                    "Error", JOptionPane.ERROR_MESSAGE);
                ultimoMensaje = "Usuario o contraseña incorrectos";
            }
        }
    }
}
