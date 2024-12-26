package Uno;

public class Deck {
    private Card[] drawPile;  // Array to hold the draw pile;
    private Card[] discardPile;  // Array to hold the discard pile;
    private int drawPileSize;  // Size of the remaining draw pile;
    public Card[] player1Deck; // Array to hold the cards of the first player;
    public Card[] player2Deck; // Array to hold the cards of the second player;

    // Constructor
    public Deck() {
        // Initialize the arrays
        drawPile = new Card[108]; // Here we assign space for the UNO deck of size 108 cards;
        discardPile = new Card[108]; // Here we assign space for the Discard Pile of size 108 cards too;
        player1Deck = new Card[7]; // Here we assign space for player1 deck of size 7 cards;
        player2Deck = new Card[7]; // Here we assign space for player2 deck of size 7 cards too;
        drawPileSize = 0; // Start with an empty draw pile;
        initializeDeck();  // Initialize the deck with UNO cards;
        initializePlayers(); // Initialize decks for two players;
        shuffle();  // Shuffle the deck after initialization;
    }

    // Method to initialize the deck with all UNO cards;
    private void initializeDeck() {
        String[] colors = {"Red", "Blue", "Green", "Yellow"};
        String[] values = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "Skip", "Reverse", "Draw Two"};

        // Add cards for each color (0-9, Skip, Reverse, Draw Two);
        int index = 0;
        for (String color : colors) {
            for (String value : values) {
                drawPile[index++] = new Card(color, value);  // Add normal cards (non-wild);
                if (!value.equals("0")) {  // Duplicate non-zero cards (e.g., two "1" Red cards);
                    drawPile[index++] = new Card(color, value);
                }
            }
        }

        // Add Wild cards and Wild Draw Four cards;
        for (int i = 0; i < 4; i++) {
            drawPile[index++] = new Card("Wild", "Wild");
            drawPile[index++] = new Card("Wild", "Draw Four");
        }

        drawPileSize = index;  // Set the size of the deck;
    }

    private void initializePlayers () {
        for (int i = 0; i < 7; i++) {
            int randomIndex = (int) (Math.random() * drawPileSize);
            Card currentCard = drawPile[randomIndex];
            player1Deck[i] = currentCard;
            // Remove currentCard, which we have just added to the player's deck, from the main deck;
            removeCardFromDrawPile(randomIndex);
        }

        for (int i = 0; i < 7; i++) {
            int randomIndex = (int) (Math.random() * drawPileSize);
            Card currentCard = drawPile[randomIndex];
            player2Deck[i] = currentCard;
            // Remove currentCard, which we have just added to the player's deck, from the main deck;
            removeCardFromDrawPile(randomIndex);
        }
    }

    // Method to remove a card from drawPile and shift remaining cards;
    private void removeCardFromDrawPile(int indexToRemove) {
        for (int i = indexToRemove; i < drawPileSize - 1; i++) {
            drawPile[i] = drawPile[i + 1]; // Shift elements left;
        }
        --drawPileSize; // Decrease the size of the draw pile;
    }

    // Method to shuffle the deck
    public void shuffle() {
        for (int i = 0; i < drawPileSize; i++) {
            // Here we generate a number in a range from 0 to 107
            int randomIndex = (int) (Math.random() * drawPileSize);
            // Swap the cards at index i and randomIndex
            Card temp = drawPile[i];
            drawPile[i] = drawPile[randomIndex];
            drawPile[randomIndex] = temp;
        }
    }

    // Method to draw a card from the draw pile
    public Card draw() {
        if (drawPileSize == 0) {
            reshuffle();  // Reshuffle if the draw pile is empty
        }
        --drawPileSize;
        return drawPile[drawPileSize];
    }

    // Method to reshuffle the discard pile into the draw pile
    private void reshuffle() {
        int discardSize = drawPileSize;
        // Move cards from discard pile back to draw pile
        for (int i = 0; i < discardSize; i++) {
            drawPile[i] = discardPile[i];
        }
        drawPileSize = discardSize;
        shuffle();  // Shuffle the new deck
    }

    // Method to add a card to the discard pile
    public void addToDiscardPile(Card card) {
        discardPile[drawPileSize] = card;  // Add the card to discard pile
    }

    // Method to check the size of the draw pile
    public int getDrawPileSize() {
        return drawPileSize;
    }

    // Method to peek at the top card of the draw pile
    public Card peekTopCard() {
        if (drawPileSize > 0) {
            return drawPile[drawPileSize - 1];
        }
        return null;  // No cards left in the draw pile
    }

    public Card peekCardAt(int index) {
        if (index >= 0 && index < drawPileSize) {
            return drawPile[index];
        }
        return null; // Return null if the index is invalid
    }
}

