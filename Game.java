package Uno;

import java.util.Scanner;

public class Game {

    public void startGame() {
        Scanner scan = new Scanner(System.in);

        // Create a new deck
        Deck deck = new Deck();

        // Shuffle the deck
        deck.shuffle();

        for (Card card : deck.player1Deck) {
            System.out.print(card + " ");
        }

        System.out.println();

        for (Card card : deck.player2Deck) {
            System.out.print(card + " ");
        }

        System.out.println();

        System.out.println("--- New Game ---");

        System.out.print("Enter name for Player1: ");
        String player1_Name = scan.nextLine();

        while (player1_Name.isEmpty()) {
            System.out.println("The name cannot be blank!");
            System.out.print("Enter name for Player1: ");
            player1_Name = scan.nextLine();
        }
        Player1 player1 = new Player1(player1_Name);


        System.out.print("Enter name for Player2: ");
        String player2_Name = scan.nextLine();
        while (player2_Name.isEmpty()) {
            System.out.println("The name cannot be blank!");
            System.out.print("Enter name for Player2: ");
            player2_Name = scan.nextLine();
        }
        Player2 player2 = new Player2(player2_Name);

        System.out.println("Welcome Player1: " + player1.name);
        System.out.println("Welcome Player2: " + player2.name);

    }


}
