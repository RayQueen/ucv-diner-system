package controllers;

import view.ConsultarMenusView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultarMenusController {
    private ConsultarMenusView view;

    public ConsultarMenusController(ConsultarMenusView view) {
        this.view = view;
        this.view.botonMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.panelLateral.setVisible(!view.panelLateral.isVisible());
                view.revalidate();
                view.repaint();
            }
        });
        // Aquí puedes agregar más listeners para los demás botones
    }
}
