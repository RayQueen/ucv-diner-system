import view.IniciarSesionView;

public class Main {
    public static void main(String[] args) {
        IniciarSesionView iniciarSesionView = new IniciarSesionView();
        models.Usuario usuarioModel = new models.Usuario("src/models/usuarios.txt");
        new controllers.IniciarSesionController(iniciarSesionView, usuarioModel);
        iniciarSesionView.setBounds(0, 0, 500, 400);
        iniciarSesionView.setVisible(true);
        iniciarSesionView.setResizable(false);
        iniciarSesionView.setLocationRelativeTo(null);
    }
}
