package controllers;

import view.PantallaInicioView;
import view.ConsultarMenusView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.UsuarioRegistrado;

public class PantallaInicioController {
    private PantallaInicioView view;
    private UsuarioRegistrado usuario;
    public PantallaInicioController(PantallaInicioView view, UsuarioRegistrado usuario) {
        this.view = view;
        this.usuario = usuario;
        this.view.actualizarUsuario(usuario);
        this.view.botonMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.panelLateral.setVisible(!view.panelLateral.isVisible());
                view.revalidate();
                view.repaint();
            }
        });
        this.view.botonConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                ConsultarMenusView view = new ConsultarMenusView();
                new ConsultarMenusController(view, usuario);
                view.setVisible(true);
                view.setBounds(0, 0, 500, 400);
                view.setVisible(true);
                view.setResizable(false);
                view.setLocationRelativeTo(null);
            }
        });
    }
    
}
