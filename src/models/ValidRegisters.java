package models;

import java.io.*;

public class ValidRegisters {
    private String[] validIDs;
    private boolean[] isAdmin;

    public ValidRegisters(String filePath) {
        loadIDs(filePath);
    }

    private void loadIDs(String filePath) {
        try {
            BufferedReader brCount = new BufferedReader(new FileReader(filePath));
            int totalUsers = 0;
            while (brCount.readLine() != null) {
                totalUsers++;
            }
            brCount.close();
            validIDs = new String[totalUsers];
            isAdmin = new boolean[totalUsers];
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            int index = 0;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split(",");
                validIDs[index] = parts[0].trim();
                if (parts[1].trim().equalsIgnoreCase("true")) {
                    isAdmin[index] = true;
                } else {
                    isAdmin[index] = false;
                }
                index++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isIDValid(String ID) {
        for (String validID : validIDs) {
            if (validID.equals(ID)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAdmin(String ID) {
        for (int i = 0; i < validIDs.length; i++) {
            if (validIDs[i].equals(ID)) {
                return isAdmin[i];
            }
        }
        return false;
    }
}
