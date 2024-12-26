package Uno;

public class Card {
    private String color; // "Red", "Blue", "Green", "Yellow", "Wild"
    private String value; // "1", "2", "Skip", "Reverse", "Draw Two", etc.

    public Card(String color, String value) {
        this.color = color;
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public String getValue() {
        return value;
    }

    public boolean isCorrect(Card topCard) {
        return this.color.equals(topCard.getColor()) || this.value.equals(topCard.getValue()) || this.color.equals("Wild");
    }

    @Override
    public String toString() {
        return color + " " + value;
    }
}

