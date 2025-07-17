package models;

public class UsuarioRegistrado {
    private String usuario;
    private String contrasena;
    private String nombreCompleto;
    private double saldo;
    private boolean admin;

    public UsuarioRegistrado(String usuario, String contrasena, String nombreCompleto, double saldo, boolean admin) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.nombreCompleto = nombreCompleto;
        this.saldo = saldo;
        this.admin = admin;
    }

    public String getNombreCompleto() { return nombreCompleto; }
    public double getSaldo() { return saldo; }
    public boolean esAdmin() { return admin; }
    public String getUsuario() { return usuario; }
    public String getContrasena() { return contrasena; }
    // ...otros métodos y constructor...

}
