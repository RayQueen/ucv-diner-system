import view.LogInView;

public class Main {
    public static void main(String[] args) {
        try {
            System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);
        } catch (UnsatisfiedLinkError e) {
            System.err.println("No se pudo cargar la librería nativa de OpenCV. Verifica que el archivo DLL esté en el PATH o usa -Djava.library.path.\n" + e.getMessage());
            javax.swing.JOptionPane.showMessageDialog(null,
                "No se pudo cargar la librería nativa de OpenCV. Verifica la configuración antes de continuar.",
                "Error OpenCV", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        LogInView logInView = new LogInView();
        models.ValidUsers usuarioModel = new models.ValidUsers();
        new controllers.LogInController(logInView, usuarioModel);
        logInView.setVisible(true);
    }
}
