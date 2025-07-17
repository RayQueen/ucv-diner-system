package view;
import javax.swing.*;

public class ValidacionCIView extends JFrame {
    public JLabel label1;
    public JTextField cedula;
    public JButton enviar;

    public ValidacionCIView() {
        setLayout(null);
        setTitle("Validación de credenciales");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label1 = new JLabel("Ingresa tu cédula de identidad");
        label1.setBounds(35, 5, 300, 30);
        add(label1);

        cedula = new JTextField();
        cedula.setBounds(10, 70, 200, 30);
        add(cedula);

        // Solo permitir números en el campo cedula
        ((javax.swing.text.AbstractDocument) cedula.getDocument()).setDocumentFilter(new javax.swing.text.DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, javax.swing.text.AttributeSet attr) throws javax.swing.text.BadLocationException {
                if (string.matches("\\d+")) {
                    super.insertString(fb, offset, string, attr);
                }
            }
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs) throws javax.swing.text.BadLocationException {
                if (text.matches("\\d+")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        enviar = new JButton("ENVIAR");
        enviar.setBounds(10, 120, 200, 30);
        add(enviar);
    }
}
