package view;

import javax.swing.*;
import java.awt.*;

public class ConsultarMenusView extends JFrame {
    public JPanel panelLateral;
    public JPanel panelSuperior;
    public JPanel panelContenido;
    public JButton botonMenu;
    public JLabel etiquetaBienvenida;
    public JButton botonRecargar;
    public JButton botonConsultar;
    public JButton botonPrePagar;

    public ConsultarMenusView() {
        setTitle("Consultar Menús - Sabor Central UCV");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        etiquetaBienvenida = new JLabel("BIENVENIDO Nombre Apellido");
        etiquetaBienvenida.setFont(new Font("Arial", Font.BOLD, 20));
        botonMenu = new JButton("☰");
        botonMenu.setFont(new Font("Arial", Font.PLAIN, 20));
        panelSuperior.add(etiquetaBienvenida, BorderLayout.WEST);
        panelSuperior.add(botonMenu, BorderLayout.EAST);

        panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        JLabel tituloPantalla = new JLabel("Consultar menús");
        tituloPantalla.setFont(new Font("Arial", Font.BOLD, 24));
        tituloPantalla.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenido.add(tituloPantalla);
        panelContenido.add(Box.createRigidArea(new Dimension(0, 20)));
        panelContenido.add(crearPanelTurno(
            "Turno Desayuno (7:00 AM - 11:00 AM)",
            "Sopa: Crema de auyama",
            "Seco: Pabellón criollo",
            "Jugo: Jugo de papelón con limón",
            "Postre: Manzana y durazno"
        ));
        panelContenido.add(Box.createRigidArea(new Dimension(0, 25)));
        panelContenido.add(crearPanelTurno(
            "Turno Almuerzo (12:00 PM - 5:00 PM)",
            "Sopa: Sopa de lentejas",
            "Seco: Pabellón criollo",
            "Jugo: Papelón con limón",
            "Postre: Quesillo"
        ));

        panelLateral = new JPanel();
        panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
        panelLateral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelLateral.setPreferredSize(new Dimension(200, 0));
        botonRecargar = new JButton("Recargar wallet");
        botonConsultar = new JButton("Consultar menús");
        botonPrePagar = new JButton("Pre-pagar plato");
        panelLateral.add(new JLabel("Menú Principal"));
        panelLateral.add(Box.createRigidArea(new Dimension(0, 15)));
        panelLateral.add(botonRecargar);
        panelLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLateral.add(botonConsultar);
        panelLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLateral.add(botonPrePagar);
        panelLateral.setVisible(false);

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(panelContenido), BorderLayout.CENTER);
        add(panelLateral, BorderLayout.EAST);
    }

    private JPanel crearPanelTurno(String titulo, String sopa, String seco, String jugo, String postre) {
        JPanel panelTurno = new JPanel(new BorderLayout(10, 10));
        panelTurno.setBorder(BorderFactory.createTitledBorder(titulo));
        JPanel panelDetalles = new JPanel();
        panelDetalles.setLayout(new BoxLayout(panelDetalles, BoxLayout.Y_AXIS));
        panelDetalles.add(new JLabel(sopa));
        panelDetalles.add(Box.createRigidArea(new Dimension(0, 5)));
        panelDetalles.add(new JLabel(seco));
        panelDetalles.add(Box.createRigidArea(new Dimension(0, 5)));
        panelDetalles.add(new JLabel(jugo));
        panelDetalles.add(Box.createRigidArea(new Dimension(0, 5)));
        panelDetalles.add(new JLabel(postre));
        JPanel panelBoton = new JPanel(new GridBagLayout());
        JButton botonPrepagar = new JButton("Prepagar");
        panelBoton.add(botonPrepagar);
        panelTurno.add(panelDetalles, BorderLayout.CENTER);
        panelTurno.add(panelBoton, BorderLayout.EAST);
        return panelTurno;
    }
}
