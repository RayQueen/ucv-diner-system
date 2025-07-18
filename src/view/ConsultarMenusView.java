package view;

import javax.swing.*;
import java.awt.*;

public class ConsultarMenusView extends JFrame {
    // ...existing code...
    public JPanel panelLateral;
    public JPanel panelSuperior;
    public JPanel panelContenido;
    public JButton botonMenu;
    public JLabel etiquetaBienvenida;
    public JButton botonRecargar;
    public JButton botonInicio;
    public JButton botonPrePagar;

    // Método para actualizar la pantalla según el usuario
    public void actualizarUsuario(models.UsuarioRegistrado usuario) {
        etiquetaBienvenida.setText("BIENVENIDO/A " + usuario.getNombreCompleto());
        if (usuario.esAdmin()) {
            botonRecargar.setVisible(false);
            botonPrePagar.setVisible(false);
        } else {
            botonRecargar.setVisible(true);
            botonPrePagar.setVisible(true);
        }
    }

    public ConsultarMenusView() {
        setTitle("Consultar Menús - Sabor Central UCV");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Paleta de colores
        Color azulOscuro = new Color(32, 61, 112);
        Color blanco = Color.WHITE;
        Color grisClaro = new Color(240, 240, 240);

        panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        panelSuperior.setBackground(azulOscuro);

        etiquetaBienvenida = new JLabel("BIENVENIDO Nombre Apellido");
        etiquetaBienvenida.setFont(new Font("Arial", Font.BOLD, 20));
        etiquetaBienvenida.setForeground(blanco);

        botonMenu = new JButton("☰");
        botonMenu.setFont(new Font("Arial", Font.PLAIN, 20));
        botonMenu.setForeground(blanco);
        botonMenu.setBackground(azulOscuro);
        botonMenu.setBorderPainted(false);
        botonMenu.setFocusPainted(false);

        panelSuperior.add(etiquetaBienvenida, BorderLayout.WEST);
        panelSuperior.add(botonMenu, BorderLayout.EAST);

        panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        panelContenido.setBackground(grisClaro);

        JLabel tituloPantalla = new JLabel("Consultar menús");
        tituloPantalla.setFont(new Font("Arial", Font.BOLD, 24));
        tituloPantalla.setAlignmentX(Component.CENTER_ALIGNMENT);
        tituloPantalla.setForeground(azulOscuro);

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
        panelLateral.setBackground(azulOscuro);

        JLabel menuTitulo = new JLabel("Menú Principal");
        menuTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        menuTitulo.setForeground(blanco);

        botonInicio = new JButton("Inicio");
        botonInicio.setBackground(blanco);
        botonInicio.setForeground(azulOscuro);

        botonRecargar = new JButton("Recargar wallet");
        botonRecargar.setBackground(blanco);
        botonRecargar.setForeground(azulOscuro);

        botonPrePagar = new JButton("Pre-pagar plato");
        botonPrePagar.setBackground(blanco);
        botonPrePagar.setForeground(azulOscuro);

        panelLateral.add(menuTitulo);
        panelLateral.add(Box.createRigidArea(new Dimension(0, 15)));
        panelLateral.add(botonRecargar);
        panelLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLateral.add(botonInicio);
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
        panelTurno.setBackground(Color.WHITE);

        JPanel panelDetalles = new JPanel();
        panelDetalles.setLayout(new BoxLayout(panelDetalles, BoxLayout.Y_AXIS));
        panelDetalles.setBackground(Color.WHITE);

        JLabel labelSopa = new JLabel(sopa);
        JLabel labelSeco = new JLabel(seco);
        JLabel labelJugo = new JLabel(jugo);
        JLabel labelPostre = new JLabel(postre);

        labelSopa.setFont(new Font("Arial", Font.PLAIN, 16));
        labelSeco.setFont(new Font("Arial", Font.PLAIN, 16));
        labelJugo.setFont(new Font("Arial", Font.PLAIN, 16));
        labelPostre.setFont(new Font("Arial", Font.PLAIN, 16));

        panelDetalles.add(labelSopa);
        panelDetalles.add(Box.createRigidArea(new Dimension(0, 5)));
        panelDetalles.add(labelSeco);
        panelDetalles.add(Box.createRigidArea(new Dimension(0, 5)));
        panelDetalles.add(labelJugo);
        panelDetalles.add(Box.createRigidArea(new Dimension(0, 5)));
        panelDetalles.add(labelPostre);

        JPanel panelBoton = new JPanel(new GridBagLayout());
        panelBoton.setBackground(Color.WHITE);
        JButton botonPrepagar = new JButton("Prepagar");
        panelBoton.add(botonPrepagar);

        panelTurno.add(panelDetalles, BorderLayout.CENTER);
        panelTurno.add(panelBoton, BorderLayout.EAST);
        return panelTurno;
    }
}
