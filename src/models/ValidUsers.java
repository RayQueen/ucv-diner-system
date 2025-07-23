package models;

import java.io.*;

public class ValidUsers {
    public RegisteredUser[] validUsers;

    public ValidUsers(String filePath) {
        loadUsers(filePath);
    }

    private void loadUsers(String filePath) {
        try {
            BufferedReader brCount = new BufferedReader(new FileReader(filePath));
            int totalUsers = 0;
            while (brCount.readLine() != null) {
                totalUsers++;
            }
            brCount.close();
            validUsers = new RegisteredUser[totalUsers];
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String user = parts[0].trim();
                    String password = parts[1].trim();
                    String fullName = parts[2].trim();
                    double balance = Double.parseDouble(parts[3].trim());
                    boolean admin = Boolean.parseBoolean(parts[4].trim());
                    String email = parts[5].trim();
                    validUsers[index] = new RegisteredUser(user, password, fullName, balance, admin, email);
                    index++;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isUserValid(String user, String password) {
        for (int i = 0; i < validUsers.length; i++) {
            if (validUsers[i].getUser().equals(user)
                && validUsers[i].getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public RegisteredUser findRegisteredUser(String user) {
        for (RegisteredUser registeredUser : validUsers) {
            if (registeredUser.getUser().equals(user)) {
                return registeredUser;
            }
        }
        return null;
    }
}
