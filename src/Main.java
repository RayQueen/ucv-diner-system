import view.LogInView;

public class Main {
    public static void main(String[] args) {
        LogInView iniciarSesionView = new LogInView();
        models.ValidUsers usuarioModel = new models.ValidUsers("src/models/validUsers.txt");
        new controllers.LogInController(iniciarSesionView, usuarioModel);
        iniciarSesionView.setBounds(0, 0, 500, 400);
        iniciarSesionView.setVisible(true);
        iniciarSesionView.setResizable(false);
        iniciarSesionView.setLocationRelativeTo(null);
    }
}
