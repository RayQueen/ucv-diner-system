package controllers;

import view.ConsultarMenusView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultarMenusController {
    private ConsultarMenusView view;
    private models.UsuarioRegistrado usuario;

    public ConsultarMenusController(ConsultarMenusView view, models.UsuarioRegistrado usuario) {
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
    }
}
