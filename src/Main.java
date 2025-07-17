import view.IniciarSesionView;
import controllers.IniciarSesionController;
import models.Usuario;

public class Main {
    public static void main(String[] args) {
        Usuario usuarioModel = new Usuario("src/models/usuarios.txt");
        IniciarSesionView iniciarSesionView = new IniciarSesionView();
        IniciarSesionController iniciarSesionController = new IniciarSesionController(iniciarSesionView, usuarioModel);

        iniciarSesionView.setBounds(0, 0, 500, 400);
        iniciarSesionView.setVisible(true);
        iniciarSesionView.setResizable(false);
        iniciarSesionView.setLocationRelativeTo(null);
    }
}
