// Ihor Ivanchenko : 3156686
package griffith;

public class Deck {
    private Card[] drawPile;  // Array to hold the draw pile;
    private Card[] discardPile;  // Array to hold the discard pile;
    private int drawPileSize;  // Size of the remaining draw pile;
    private int discardPileSize; // Size of the played cards;
    public Card[] player1Deck; // Array to hold the cards of the first player;
    public Card[] player2Deck; // Array to hold the cards of the second player;
    public Card currentCard; // A current card on the table;

    // Constructor;
    public Deck() {
        // Initialize the arrays
        drawPile = new Card[108]; // Here we assign space for the UNO deck of size 108 cards;
        discardPile = new Card[108]; // Here we assign space for the Discard Pile of size 108 cards too;
        player1Deck = new Card[7]; // Here we assign space for player1 deck of size 7 cards;
        player2Deck = new Card[7]; // Here we assign space for player2 deck of size 7 cards too;
        drawPileSize = 0; // Start with an empty draw pile;
        discardPileSize = 0; // Start with an empty discard pile;
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

        drawPileSize = index;  // Set the size of the deck without Wild cards and Wild Draw Four cards;

        int randomIndex = (int) (Math.random() * drawPileSize);
        currentCard = drawPile[randomIndex];

        // Add Wild cards and Wild Draw Four cards;
        for (int i = 0; i < 4; i++) {
            drawPile[index++] = new Card("Wild", "Wild");
            drawPile[index++] = new Card("Wild", "Draw Four");
        }

        drawPileSize = index;  // Set the starting size of the deck;

        removeCardFromDrawPile(randomIndex);
    }

    // Method to assign 7 cards to each player deck;
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

    // Method to shuffle the deck;
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

    // Method to reshuffle the discard pile into the draw pile;
    private void reshuffle() {
        int discardSize = discardPileSize; // Get the number of cards in the discard pile;

        // Move cards from discard pile back to draw pile;
        for (int i = 0; i < discardSize; i++) {
            drawPile[drawPileSize + i] = discardPile[i]; // Add cards to the end of the draw pile;
        }

        drawPileSize += discardSize; // Update the draw pile size;
        shuffle(); // Shuffle the new deck;
    }

    // Method to add a card to the discard pile;
    public void addToDiscardPile(Card card) {
        if (discardPileSize >= discardPile.length) {
            reshuffle();
        }
        discardPile[discardPileSize] = card;  // Add the card to discard pile;
        discardPileSize++; // Increment the size of the array;
    }

    // Method to peek at the top card of the draw pile;
    public Card[] peekTopCard(Card[] playerDeck) {
        Card[] newDeck = new Card[playerDeck.length + 1];
        int index = 0;
        for (Card card : playerDeck) {
            newDeck[index++] = card;
        }

        if (drawPileSize <= 0) {
            reshuffle();  // Reshuffle if no cards remain;
        }
        newDeck[newDeck.length - 1] = drawPile[drawPileSize - 1];
        removeCardFromDrawPile(drawPileSize - 1);

        return newDeck;
    }

    // Method to add two cards to the opponent if "Draw Two";
    public Card[] drawTwo(Card[] playerDeck) {
        for (int i = 0; i < 2; i++) {
            playerDeck = peekTopCard(playerDeck);
        }
        return playerDeck;
    }

    // Method to add four cards to the opponent if "Draw Four";
    public Card[] drawFour(Card[] playerDeck) {
        for (int i = 0; i < 4; i++) {
            playerDeck = peekTopCard(playerDeck);
        }
        return playerDeck;
    }

    // Method to remove selected card from deck;
    public Card[] removeCardFromDeck(Card[] playerDeck, int index) {
        Card[] newDeck = new Card[playerDeck.length - 1];
        for (int i = 0, j = 0; i < playerDeck.length; i++) {
            if (i != index) {
                newDeck[j++] = playerDeck[i];
            }
        }
        return newDeck;
    }

}

