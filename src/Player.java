// Ihor Ivanchenko : 3156686
package griffith;

public class Player {
    private final String name;
    private boolean currentTurn;

    // A constructor for the Player class;
    public Player(String name, boolean currentTurn) {
        this.name = name;
        this.currentTurn = currentTurn;
    }

    // Method to return the name of the current instance of the player;
    public String getName() {
        return name;
    }

    // Method to return the currentTurn value of the current instance of the player;
    public boolean isCurrentTurn() {
        return currentTurn;
    }

    // Method to set the currentTurn value of the current instance of the player;
    public void setCurrentTurn(boolean currentTurn) {
        this.currentTurn = currentTurn;
    }
}
