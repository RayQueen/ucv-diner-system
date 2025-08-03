package models;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
    public Turn[] menus;

    public Menu() {
        loadMenu("src/models/data/menu.txt");
    }

    private void loadMenu(String filePath) {
        try {
            int totalTurns = 2;
            this.menus = new Turn[totalTurns];
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String turn = parts[0].trim();
                    String soup = parts[1].trim();
                    String dry = parts[2].trim();
                    String drink = parts[3].trim();
                    String dessert = parts[4].trim();
                    this.menus[index] = new Turn(turn, soup, dry, drink, dessert);
                    index++;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Turn getBreakfast() {
        return menus[0];
    }

    public Turn getLunch() {
        return menus[1];
    }
}
