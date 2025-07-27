package models;

import java.io.*;

public class ValidRegisters {
    private String[] validIDs;
    private int[] userType;

    public ValidRegisters() {
        loadIDs();
    }

    private void loadIDs() {
        try {
            BufferedReader brCount = new BufferedReader(new FileReader("src/models/data/validRegisters.txt"));
            int totalUsers = 0;
            while (brCount.readLine() != null) {
                totalUsers++;
            }
            brCount.close();
            validIDs = new String[totalUsers];
            userType = new int[totalUsers];
            BufferedReader br = new BufferedReader(new FileReader("src/models/data/validRegisters.txt"));
            String line;
            int index = 0;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split(",");
                validIDs[index] = parts[0].trim();
                try {
                    userType[index] = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    userType[index] = 0;
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

    public int getUserType(String ID) {
        for (int i = 0; i < validIDs.length; i++) {
            if (validIDs[i].equals(ID)) {
                return userType[i];
            }
        }
        return 0;
    }
}
