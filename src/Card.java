// Ihor Ivanchenko : 3156686
package griffith;

public class Card {
    final private String color; // "Red", "Blue", "Green", "Yellow", "Wild";
    public String value; // "1", "2", "Skip", "Reverse", "Draw Two";
    public int uniqueNumber;

    // A constructor for the Card class;
    public Card(String color, String value) {
        this.color = color;
        this.value = value;
    }

    // Method to return the color of the chosen card;
    public String getColor() {
        return color;
    }

    // Method to return the value of the chosen card;
    public String getValue() {
        return value;
    }

    // A method, which draws the card. Basically it takes the chosen card values and structure them into String;
    @Override
    public String toString() {
        return "┌────────────┐\n" +
                String.format("│ %-10s │\n", value) +
                String.format("│ %-10s │\n", color) +
                "└────────────┘\n";
    }
}

