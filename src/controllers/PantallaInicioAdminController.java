package controllers;

import view.PantallaInicioAdminView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaInicioAdminController {
    private PantallaInicioAdminView view;
    public PantallaInicioAdminController(PantallaInicioAdminView view) {
        this.view = view;
        this.view.botonMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.panelLateral.setVisible(!view.panelLateral.isVisible());
                view.revalidate();
                view.repaint();
            }
        });
        // Aquí puedes agregar listeners para los botones de la barra lateral
        this.view.botonGestionarMenu.addActionListener(e -> {
            // Lógica para gestionar menú
        });
        this.view.botonCalcularCCB.addActionListener(e -> {
            // Lógica para calcular CCB
        });
        this.view.botonEstablecerTarifas.addActionListener(e -> {
            // Lógica para establecer tarifas
        });
        this.view.botonIngresarConsumo.addActionListener(e -> {
            // Lógica para ingresar consumo diario
        });
        this.view.botonGenerarReportes.addActionListener(e -> {
            // Lógica para generar reportes
        });
    }
}
