import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import javax.swing.*;
import javax.swing.text.NumberFormatter;

/**
 * Esta clase crea la interfaz para una pantalla de configuración de costos.
 * Permite al usuario ingresar costos fijos y variables (solo números) y
 * guardarlos en un archivo de texto.
 */
public class ConfiguracionCostosApp extends JFrame {

    private JPanel panelLateral;
    private JFormattedTextField campoCostoFijo;
    private JFormattedTextField campoCostoVariable;

    public ConfiguracionCostosApp() {
        // --- 1. Configuración de la ventana principal ---
        setTitle("Configuración de Costos - Sabor Central UCV");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- 2. Panel superior (reutilizado) ---
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        JLabel etiquetaBienvenida = new JLabel("BIENVENIDO Nombre Apellido");
        etiquetaBienvenida.setFont(new Font("Arial", Font.BOLD, 20));
        JButton botonMenu = new JButton("☰");
        botonMenu.setFont(new Font("Arial", Font.PLAIN, 20));
        panelSuperior.add(etiquetaBienvenida, BorderLayout.WEST);
        panelSuperior.add(botonMenu, BorderLayout.EAST);

        // --- 3. Panel central para el formulario de costos ---
        JPanel panelContenido = new JPanel(new GridBagLayout());
        panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espacio entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel tituloPantalla = new JLabel("Configuración de Costos");
        tituloPantalla.setFont(new Font("Arial", Font.BOLD, 24));

        // --- Creación de los campos de texto que solo aceptan números ---
        NumberFormat formatoNumero = NumberFormat.getNumberInstance();
        formatoNumero.setGroupingUsed(false); // Sin comas para miles
        NumberFormatter formatoSoloNumeros = new NumberFormatter(formatoNumero);
        formatoSoloNumeros.setValueClass(Double.class);
        formatoSoloNumeros.setAllowsInvalid(false); // No permite escribir caracteres inválidos

        // Campo Costo Fijo
        JLabel etiquetaCostoFijo = new JLabel("Costo Fijo:");
        campoCostoFijo = new JFormattedTextField(formatoSoloNumeros);
        campoCostoFijo.setColumns(15); // Ancho del campo

        // Campo Costo Variable
        JLabel etiquetaCostoVariable = new JLabel("Costo Variable:");
        campoCostoVariable = new JFormattedTextField(formatoSoloNumeros);
        campoCostoVariable.setColumns(15);

        JButton botonGuardar = new JButton("Guardar");

        // --- Añadir componentes al panel central usando GridBagLayout ---
        gbc.gridwidth = 2; // Ocupa dos columnas
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panelContenido.add(tituloPantalla, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy = 1;
        panelContenido.add(etiquetaCostoFijo, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelContenido.add(campoCostoFijo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panelContenido.add(etiquetaCostoVariable, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelContenido.add(campoCostoVariable, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panelContenido.add(botonGuardar, gbc);


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
        panelLateral.setVisible(false);

        // --- 5. Lógica para los botones ---
        botonMenu.addActionListener(e -> {
            panelLateral.setVisible(!panelLateral.isVisible());
            revalidate();
            repaint();
        });

        botonGuardar.addActionListener(e -> guardarDatos());

        // --- 6. Añadir todos los paneles a la ventana ---
        add(panelSuperior, BorderLayout.NORTH);
        add(panelContenido, BorderLayout.CENTER);
        add(panelLateral, BorderLayout.EAST);
    }

    /**
     * Toma los datos de los campos de texto y los guarda en "costos.txt".
     * El archivo se sobrescribe en cada llamada.
     */
    private void guardarDatos() {
        String costoFijo = campoCostoFijo.getText();
        String costoVariable = campoCostoVariable.getText();

        if (costoFijo.isEmpty() || costoVariable.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ambos campos deben tener un valor.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // El try-with-resources se encarga de cerrar el FileWriter automáticamente
        try (FileWriter writer = new FileWriter("costos.txt", false)) { // false para sobrescribir
            writer.write("Costo Fijo: " + costoFijo + "\n");
            writer.write("Costo Variable: " + costoVariable + "\n");

            JOptionPane.showMessageDialog(this,
                    "Datos guardados correctamente en el archivo costos.txt",
                    "Guardado Exitoso",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Ocurrió un error al guardar el archivo: " + ex.getMessage(),
                    "Error de Guardado",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método principal para iniciar la aplicación.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConfiguracionCostosApp().setVisible(true));
    }
}