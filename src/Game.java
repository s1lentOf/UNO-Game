// Ihor Ivanchenko : 3156686
package griffith;

import java.util.Locale;
import java.util.Scanner;

public class Game {

    private Player player1;
    private Player player2;
    public int turnNumber = 1;
    public String gameDifficulty;

    Scanner scan = new Scanner(System.in);

    // Main method of the game;
    public void startGame() {
        // Create a new deck;
        Deck deck = new Deck();

        // Shuffle the deck;
        deck.shuffle();

        System.out.println("--- New Game ---\n");

        // Initialize two players;
        System.out.print("Enter name for Player1: ");
        String player1Name = getValidName(scan);
        player1 = new Player(player1Name, true);

        System.out.print("Enter name for Player2: ");
        String player2Name = getValidName(scan);
        player2 = new Player(player2Name, false);

        System.out.println("Choose the difficulty of the game.\n'1' - easy\n'2' - hard");
        System.out.print("Your choice: ");
        String userDifficultyChoice = scan.nextLine().trim();
        while (!userDifficultyChoice.equals("1") && !userDifficultyChoice.equals("2")) {
            System.out.print("Your choice: ");
            userDifficultyChoice = scan.nextLine().trim();
        }

        if (userDifficultyChoice.equals("1")) {
            gameDifficulty = "Easy";
        } else {
            gameDifficulty = "Hard";
        }

        System.out.println("Welcome Player1: " + player1.getName());
        System.out.println("Welcome Player2: " + player2.getName());

        // Game loop;
        while (deck.player1Deck.length > 0 && deck.player2Deck.length > 0) {
            System.out.println("\nTURN: " + turnNumber + "\n");

            // Here we check which player has currentTurn assigned to true;
            // This way we can implement the functionality of 'skip' and 'reverse' cards;
            if (player1.isCurrentTurn()) {
                playTurn(scan, player1, deck, deck.player1Deck);
                if (deck.player1Deck.length == 0) {
                    System.out.println("        HURRAY!");
                    System.out.println("    Congratulations!");
                    System.out.println(player1.getName() + " wins!");
                    break;
                }
            } else {
                playTurn(scan, player2, deck, deck.player2Deck);
                if (deck.player2Deck.length == 0) {
                    System.out.println("            HURRAY!");
                    System.out.println("        Congratulations!");
                    System.out.println("    " + player2.getName() + " wins!");
                    break;
                }
            }

            turnNumber++;
        }
    }

    // Method to play each turn;
    private void playTurn(Scanner scan, Player player, Deck deck, Card[] playerDeck) {
        System.out.println(player.getName());

        boolean isValidChoice = false;
        boolean hasTakenCard = false;
        boolean invalidChoice = false;

        while (!isValidChoice) {

            if (!invalidChoice) {
                drawCards(playerDeck, deck);
            }

            if (!hasTakenCard) {
                System.out.println("To place a card type '1', to peek top card type '2'");
            } else {
                System.out.println("To place a card type '1'\nTo pass type 'p'");
            }

            System.out.print("Your choice: ");
            String mainChoice = scan.nextLine();

            while (!mainChoice.equals("1") && !mainChoice.equals("2") && !mainChoice.equals("p")) {
                System.out.print("Your choice: ");
                mainChoice = scan.nextLine();
            }

            if (mainChoice.equals("2")) {
                if (player.getName().equals(player1.getName())) {
                    deck.player1Deck = deck.peekTopCard(playerDeck);
                    playerDeck = deck.player1Deck;
                } else {
                    deck.player2Deck = deck.peekTopCard(playerDeck);
                    playerDeck = deck.player2Deck;
                }
                hasTakenCard = true;
                continue;
            } else if (mainChoice.equals("p")) {
                changeTheDirection(player);
                break;
            }

            System.out.print("\nType 'm' to come back to the menu.\nChoose a card (1-" + playerDeck.length + "): ");
            mainChoice = scan.nextLine().trim();
            int choice = -1;

            if (mainChoice.equals("m")) {
                continue;
            }

            try {
                // Split the input by the comma
                String[] parts = mainChoice.split(",");
                // Trim to remove extra whitespace and parse the first part as an integer
                choice = Integer.parseInt(parts[0]) - 1; // Convert to 0-based index
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid input format.");
            }

            // If the choice is in the range of current player deck;
            if (isValidCardChoice(choice, playerDeck)) {
                Card chosenCard = playerDeck[choice];

                // If correct card was chosen, and it can be played;
                if (correctCard(chosenCard, deck)) {
                    processChosenCard(chosenCard, deck);

                    // Remove chosen card from the respective player deck;
                    if (player.getName().equals(player1.getName())) {
                        deck.player1Deck = deck.removeCardFromDeck(playerDeck, choice);
                        // Check if the user has typed 'UNO'
                        if (deck.player1Deck.length == 1) {
                            if (gameDifficulty.equals("Easy")) {
                                remindUserToTypeUno();
                            } else {
                                if (!mainChoice.toLowerCase().contains("uno")) {
                                    System.out.println("You have 1 card left and you forgot to type 'UNO'. You get a penalty of 2 cards.");
                                    deck.player1Deck = deck.drawTwo(deck.player1Deck);
                                }
                            }
                        }
                    } else {
                        deck.player2Deck = deck.removeCardFromDeck(playerDeck, choice);
                        // Check if the user has typed 'UNO'
                        if (deck.player2Deck.length == 1) {
                            if (gameDifficulty.equals("Easy")) {
                                remindUserToTypeUno();
                            } else {
                                if (!mainChoice.toLowerCase().contains("uno")) {
                                    System.out.println("You have 1 card left and you forgot to type 'UNO'. You get a penalty of 2 cards.");
                                    deck.player2Deck = deck.drawTwo(deck.player2Deck);
                                }
                            }
                        }
                    }



                    // Check for the special card;
                    specialCard(chosenCard, deck, player);
                    isValidChoice = true;
                } else {
                    System.out.println("Invalid card. Choose a card with matching color or value.\n");
                    invalidChoice = true;
                }
            } else {
                System.out.println("Invalid choice. Please choose a valid card number.\n");
                invalidChoice = true;
            }
        }
    }

    // Method to check for the special card and execute special actions, depending on that card;
    private void specialCard(Card card, Deck deck, Player currentPlayer) {
        switch (card.getValue()) {
            case "Skip":
            case "Reverse":
                if (currentPlayer.getName().equals(player1.getName())) {
                    player1.setCurrentTurn(true);
                    player2.setCurrentTurn(false);
                } else {
                    player1.setCurrentTurn(false);
                    player2.setCurrentTurn(true);
                }
                break;
            case "Draw Two":
                if (currentPlayer.getName().equals(player1.getName())) {
                    deck.player2Deck = deck.drawTwo(deck.player2Deck);
                    changeTheDirection(currentPlayer);
                } else {
                    deck.player1Deck = deck.drawTwo(deck.player1Deck);
                    changeTheDirection(currentPlayer);
                }
                break;
            case "Wild":
                card.value = getColorChoice(scan);
                changeTheDirection(currentPlayer);
                break;

            case "Draw Four":
                if (currentPlayer.getName().equals(player1.getName())) {
                    deck.player2Deck = deck.drawFour(deck.player2Deck);
                } else {
                    deck.player1Deck = deck.drawFour(deck.player1Deck);
                }

                card.value = getColorChoice(scan);
                break;

            default:
                changeTheDirection(currentPlayer);
                break;
        }
    }

    // Method to change the color if the play has chosen appropriate card;
    public static String getColorChoice(Scanner scan) {
        String userChoice;
        boolean validChoice = false;
        String color = "";

        while (!validChoice) {
            System.out.println("Choose the color by typing the number: \nYellow - 1\nRed - 2\nBlue - 3\nGreen - 4");
            System.out.print("Your choice: ");
            userChoice = scan.nextLine();
            switch (userChoice) {
                case "1":
                    color = "Yellow";
                    validChoice = true;
                    break;
                case "2":
                    color = "Red";
                    validChoice = true;
                    break;
                case "3":
                    color = "Blue";
                    validChoice = true;
                    break;
                case "4":
                    color = "Green";
                    validChoice = true;
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 4.");
            }
        }
        return color;
    }

    // Method t change the players' turn;
    private void changeTheDirection (Player player) {
        if (player.getName().equals(player1.getName())) {
            player1.setCurrentTurn(false);
            player2.setCurrentTurn(true);
        } else {
            player1.setCurrentTurn(true);
            player2.setCurrentTurn(false);
        }
    }

    // Method to process the chosen card and delete it from the players deck and add to discard pile;
    private void processChosenCard(Card chosenCard, Deck deck) {
        deck.addToDiscardPile(deck.currentCard);
        deck.currentCard = chosenCard;
    }

    // Method to check if the card was chosen in the correct range;
    private boolean isValidCardChoice(int choice, Card[] playerDeck) {
        return choice >= 0 && choice < playerDeck.length;
    }

    // Method to check if the chosen card can be played;
    private boolean correctCard(Card card, Deck deck) {
        return card.getColor().equals(deck.currentCard.getColor()) || card.getValue().equals(deck.currentCard.getValue()) || card.getValue().equals("Wild") || card.getValue().equals("Draw Four") || card.getColor().equals(deck.currentCard.getValue());
    }

    // Method to print all cards in the current player deck;
    private void drawCards(Card[] playerDeck, Deck deck) {
        for (int i = 0; i < playerDeck.length; i++) {
            playerDeck[i].uniqueNumber = i + 1;
            System.out.println(playerDeck[i] + "      " + playerDeck[i].uniqueNumber + "\n");
        }
        System.out.println("\nCurrent card: \n" + deck.currentCard + "\n");
    }

    // Method to get the valid names for each player;
    private String getValidName(Scanner scan) {
        String name = scan.nextLine().trim();
        while (name.isEmpty()) {
            System.out.println("The name cannot be blank!");
            System.out.print("Enter name: ");
            name = scan.nextLine().trim();
        }
        return name;
    }

    // Method to remind user to type UNO if the difficulty of the game is 'Easy';
    private void remindUserToTypeUno () {
        System.out.print("You have 1 card left. Type 'UNO': ");
        String unoTextUser = scan.nextLine().toLowerCase();
        while (!unoTextUser.equals("uno")) {
            System.out.print("Type 'UNO': ");
            unoTextUser = scan.nextLine().toLowerCase();
        }
    }
}
