package models;

public class UsuarioRegistrado {
    private String usuario;
    private String contrasena;
    private String nombreCompleto;
    private double saldo;
    private boolean admin;
    private String correo;

    public UsuarioRegistrado(String usuario, String contrasena, String nombreCompleto, double saldo, boolean admin, String correo) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.nombreCompleto = nombreCompleto;
        this.saldo = saldo;
        this.admin = admin;
        this.correo = correo;
    }

    public String getNombreCompleto() { return nombreCompleto; }
    public double getSaldo() { return saldo; }
    public boolean esAdmin() { return admin; }
    public String getUsuario() { return usuario; }
    public String getContrasena() { return contrasena; }
    public String getCorreo() { return correo; }

}
