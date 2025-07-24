import view.LogInView;

public class Main {
    public static void main(String[] args) {
        LogInView logInView = new LogInView();
        models.ValidUsers usuarioModel = new models.ValidUsers("src/models/validUsers.txt");
        new controllers.LogInController(logInView, usuarioModel);
        logInView.setVisible(true);
    }
}
