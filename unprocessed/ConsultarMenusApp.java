import java.awt.*;
import javax.swing.*;

/**
 * Esta clase crea la interfaz de usuario para la pantalla de "Consultar Menús"
 * de la aplicación Sabor Central UCV. Mantiene la barra superior y el menú lateral
 * de la pantalla principal.
 */
public class ConsultarMenusApp extends JFrame {

    // Panel lateral que se mostrará u ocultará
    private JPanel panelLateral;

    public ConsultarMenusApp() {
        // --- 1. Configuración de la ventana principal ---
        setTitle("Consultar Menús - Sabor Central UCV");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- 2. Panel superior (reutilizado de la pantalla anterior) ---
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel etiquetaBienvenida = new JLabel("BIENVENIDO Nombre Apellido");
        etiquetaBienvenida.setFont(new Font("Arial", Font.BOLD, 20));

        JButton botonMenu = new JButton("☰");
        botonMenu.setFont(new Font("Arial", Font.PLAIN, 20));

        panelSuperior.add(etiquetaBienvenida, BorderLayout.WEST);
        panelSuperior.add(botonMenu, BorderLayout.EAST);

        // --- 3. Panel central con los menús ---
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel tituloPantalla = new JLabel("Consultar menús");
        tituloPantalla.setFont(new Font("Arial", Font.BOLD, 24));
        tituloPantalla.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Añadir espacio después del título
        panelContenido.add(tituloPantalla);
        panelContenido.add(Box.createRigidArea(new Dimension(0, 20)));

        // --- Panel para el Turno de Almuerzo ---
        JPanel panelAlmuerzo = crearPanelTurno(
            "Turno Desayuno (7:00 AM - 11:00 AM)",
            "Sopa: Crema de auyama",
            "Seco: Pabellón criollo",
            "Jugo: Jugo de papelón con limón",
            "Postre: Manzana y durazno"
        );
        panelContenido.add(panelAlmuerzo);
        panelContenido.add(Box.createRigidArea(new Dimension(0, 25))); // Espacio entre turnos

        // --- Panel para el Turno de Cena ---
        JPanel panelCena = crearPanelTurno(
            "Turno Almuerzo (12:00 PM - 5:00 PM)",
            "Sopa: Sopa de lentejas",
            "Seco: Pabellón criollo",
            "Jugo: Papelón con limón",
            "Postre: Quesillo"
        );
        panelContenido.add(panelCena);


        // --- 4. Panel lateral (menú derecho, reutilizado) ---
        panelLateral = new JPanel();
        panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
        panelLateral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelLateral.setPreferredSize(new Dimension(200, 0));

        JButton botonRecargar = new JButton("Recargar wallet");
        JButton botonConsultar = new JButton("Consultar menús");
        JButton botonPrePagar = new JButton("Pre-pagar plato");

        panelLateral.add(new JLabel("Menú Principal"));
        panelLateral.add(Box.createRigidArea(new Dimension(0, 15)));
        panelLateral.add(botonRecargar);
        panelLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLateral.add(botonConsultar);
        panelLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLateral.add(botonPrePagar);
        
        panelLateral.setVisible(false); // Oculto inicialmente

        // --- 5. Lógica del botón de menú ---
        botonMenu.addActionListener(e -> {
            panelLateral.setVisible(!panelLateral.isVisible());
            revalidate();
            repaint();
        });

        // --- 6. Añadir todos los paneles a la ventana ---
        add(panelSuperior, BorderLayout.NORTH);
        // Usamos un JScrollPane para que el contenido sea desplazable si no cabe
        add(new JScrollPane(panelContenido), BorderLayout.CENTER);
        add(panelLateral, BorderLayout.EAST);
    }

    /**
     * Método de ayuda para crear el panel de un turno (almuerzo o cena).
     * @param titulo El título del turno (ej. "Turno Almuerzo...")
     * @param sopa Descripción de la sopa.
     * @param seco Descripción del plato principal.
     * @param jugo Descripción del jugo.
     * @param postre Descripción del postre.
     * @return Un JPanel con toda la información del turno.
     */
    private JPanel crearPanelTurno(String titulo, String sopa, String seco, String jugo, String postre) {
        JPanel panelTurno = new JPanel(new BorderLayout(10, 10));
        panelTurno.setBorder(BorderFactory.createTitledBorder(titulo));

        // Panel para los detalles del menú (izquierda)
        JPanel panelDetalles = new JPanel();
        panelDetalles.setLayout(new BoxLayout(panelDetalles, BoxLayout.Y_AXIS));
        
        panelDetalles.add(new JLabel(sopa));
        panelDetalles.add(Box.createRigidArea(new Dimension(0, 5)));
        panelDetalles.add(new JLabel(seco));
        panelDetalles.add(Box.createRigidArea(new Dimension(0, 5)));
        panelDetalles.add(new JLabel(jugo));
        panelDetalles.add(Box.createRigidArea(new Dimension(0, 5)));
        panelDetalles.add(new JLabel(postre));

        // Panel para el botón (derecha)
        JPanel panelBoton = new JPanel(new GridBagLayout()); // Usamos GridBagLayout para centrar el botón
        JButton botonPrepagar = new JButton("Prepagar");
        panelBoton.add(botonPrepagar);

        panelTurno.add(panelDetalles, BorderLayout.CENTER);
        panelTurno.add(panelBoton, BorderLayout.EAST);

        return panelTurno;
    }

    /**
     * Método principal para iniciar la aplicación.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ConsultarMenusApp().setVisible(true);
        });
    }
}