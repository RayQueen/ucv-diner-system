package controllers;

import view.CostosView;

import view.PantallaInicioAdminView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.UsuarioRegistrado;

public class PantallaInicioAdminController {
    private PantallaInicioAdminView view;
    private UsuarioRegistrado usuario;

    public PantallaInicioAdminController(PantallaInicioAdminView view, UsuarioRegistrado usuario) {
        this.view = view;
        this.usuario = usuario;
        this.view.actualizarUsuario(usuario.getNombreCompleto());
        this.view.botonMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.panelLateral.setVisible(!view.panelLateral.isVisible());
                view.revalidate();
                view.repaint();
            }
        });
        this.view.botonCalcularCCB.addActionListener(e -> {
            view.dispose();
            CostosView cview = new CostosView();
            new controllers.CostosController(cview, usuario);
            cview.setVisible(true);
            cview.setBounds(0, 0, 500, 400);
            cview.setVisible(true);
            cview.setResizable(false);
            cview.setLocationRelativeTo(null);
        });
    }
}
