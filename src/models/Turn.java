package models;

public class Turn {
    private String turn;
    private String soup;
    private String dry;
    private String juice;
    private String dessert;

    public Turn(String turn, String soup, String dry, String juice, String dessert) {
        this.turn = turn;
        this.soup = soup;
        this.dry = dry;
        this.juice = juice;
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

    public String getJuice() {
        return juice;
    }

    public String getDessert() {
        return dessert;
    }
}
