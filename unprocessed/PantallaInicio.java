import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta clase crea la interfaz de usuario para la aplicación Sabor Central UCV.
 * Se enfoca en la estructura y funcionalidad de los componentes (botones, texto)
 * en lugar del diseño detallado.
 */
public class PantallaInicio extends JFrame {

    // Panel lateral que se mostrará u ocultará
    private JPanel panelLateral;

    public PantallaInicio() {
        // --- 1. Configuración de la ventana principal ---
        setTitle("Sabor Central UCV");
        setSize(800, 600); // Tamaño inicial de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setLayout(new BorderLayout(10, 10)); // Layout principal

        // --- 2. Panel superior con bienvenida y botón de menú ---
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel etiquetaBienvenida = new JLabel("BIENVENIDO Nombre Apellido");
        etiquetaBienvenida.setFont(new Font("Arial", Font.BOLD, 20));
        
        JButton botonMenu = new JButton("☰"); // Botón para mostrar/ocultar el menú
        botonMenu.setFont(new Font("Arial", Font.PLAIN, 20));

        panelSuperior.add(etiquetaBienvenida, BorderLayout.WEST);
        panelSuperior.add(botonMenu, BorderLayout.EAST);

        // --- 3. Panel central con el contenido principal ---
        JPanel panelContenido = new JPanel();
        // Usamos BoxLayout para apilar los componentes verticalmente
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel para el Wallet
        JPanel panelWallet = new JPanel();
        panelWallet.setLayout(new BoxLayout(panelWallet, BoxLayout.Y_AXIS));
        panelWallet.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar este panel
        panelWallet.setBorder(BorderFactory.createTitledBorder("WALLET"));

        JLabel etiquetaSaldo = new JLabel("Saldo actual: BS 180,07");
        etiquetaSaldo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton botonMovimientos = new JButton("Ver últimos movimientos");
        botonMovimientos.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelWallet.add(etiquetaSaldo);
        panelWallet.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio vertical
        panelWallet.add(botonMovimientos);

        // Mensajes centrales
        JLabel etiquetaTitulo = new JLabel("Sabor Central UCV");
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        etiquetaTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel etiquetaSubtitulo = new JLabel("Nutriendo mentes, deleitando paladares.");
        etiquetaSubtitulo.setFont(new Font("Arial", Font.ITALIC, 14));
        etiquetaSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Añadir componentes al panel de contenido con espaciadores
        panelContenido.add(etiquetaTitulo);
        panelContenido.add(Box.createRigidArea(new Dimension(0, 5)));
        panelContenido.add(etiquetaSubtitulo);
        panelContenido.add(Box.createVerticalGlue()); // Espacio flexible para empujar el wallet hacia abajo
        panelContenido.add(panelWallet);
        

        // --- 4. Panel lateral (menú derecho) ---
        panelLateral = new JPanel();
        panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
        panelLateral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelLateral.setPreferredSize(new Dimension(200, 0));

        // Botones del menú lateral
        JButton botonRecargar = new JButton("Recargar wallet");
        JButton botonConsultar = new JButton("Consultar menús");
        JButton botonPrePagar = new JButton("Pre-pagar plato");

        panelLateral.add(new JLabel("Menú Principal")); // Título del menú
        panelLateral.add(Box.createRigidArea(new Dimension(0, 15)));
        panelLateral.add(botonRecargar);
        panelLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLateral.add(botonConsultar);
        panelLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLateral.add(botonPrePagar);
        
        panelLateral.setVisible(false); // Inicialmente oculto

        // --- 5. Lógica para mostrar/ocultar el panel lateral ---
        botonMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Alterna la visibilidad del panel lateral
                panelLateral.setVisible(!panelLateral.isVisible());
                
                // Revalida y repinta el contenedor principal para que los cambios se muestren
                revalidate();
                repaint();
            }
        });


        // --- 6. Añadir todos los paneles a la ventana principal ---
        add(panelSuperior, BorderLayout.NORTH);
        add(panelContenido, BorderLayout.CENTER);
        add(panelLateral, BorderLayout.EAST);
    }

    /**
     * Método principal para iniciar la aplicación.
     */
    public static void main(String[] args) {
        // Se asegura que la creación de la GUI se haga en el hilo de despacho de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PantallaInicio().setVisible(true);
            }
        });
    }
}