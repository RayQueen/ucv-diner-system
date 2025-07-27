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
        double value = this.balance + amount;
        this.balance = Math.round(value * 100.0) / 100.0;
        updateBalanceInFile();
    }
    public int getUserType() { return userType; }
    public String getUser() { return user; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }

    public void updateBalanceInFile() {
        java.util.List<String> lines = new java.util.ArrayList<>();
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("src/models/data/validUsers.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6 && parts[0].trim().equals(this.user)) {
                    parts[3] = String.valueOf(this.balance);
                    line = String.join(",", parts);
                }
                lines.add(line);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        try (java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter("src/models/data/validUsers.txt", false))) {
            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
