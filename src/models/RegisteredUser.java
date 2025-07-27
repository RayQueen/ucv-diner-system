package models;

public class RegisteredUser {
    private String user;
    private String password;
    private String fullName;
    private double balance;
    private int userType;
    private String email;

    public RegisteredUser(String user, String password, String fullName, double balance, int userType, String email) {
        this.user = user;
        this.password = password;
        this.fullName = fullName;
        this.balance = balance;
        this.userType = userType;
        this.email = email;
    }

    public String getFullName() { return fullName; }
    public double getBalance() { return balance; }
    public void addBalance(double amount) {
        if (amount > 0) {
            this.balance += amount;
        } else {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
    }
    public int getUserType() { return userType; }
    public String getUser() { return user; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
}
