package models;

public class Turn {
    private String turn;
    private String soup;
    private String dry;
    private String drink;
    private String dessert;

    public Turn(String turn, String soup, String dry, String juice, String dessert) {
        this.turn = turn;
        this.soup = soup;
        this.dry = dry;
        this.drink = juice;
        this.dessert = dessert;
    }

    public String getTurn() {
        return turn;
    }

    public String getSoup() {
        return soup;
    }

    public String getDry() {
        return dry;
    }

    public String getDrink() {
        return drink;
    }

    public String getDessert() {
        return dessert;
    }
}
