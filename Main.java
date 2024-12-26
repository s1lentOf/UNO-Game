package Uno;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("\n---------------------------------------"
                + "-----------------------------------------");
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyso+++osyhdhhhhhhhhh");
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyo:.......```-/shhhhhhhh");
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhy+..````.....````.:shhhhhh");
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhy:````-/osysso+:.```.+hhhhh");
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyso/-.shhhhh:````/yddmddhhys+-..../hhhh");
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhys:....-yhhds-```-ydhhhhddmhyho-....shhh");
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyyhhhhhhhdhs+-````/hddo.  `-hhhhhhhhhmmdh/----:hhh");
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhys+:-.-+shhhhdmys+.````+dds- ``.shhhhhhhhhmmd/::::-hhh");
        System.out.println("hhhhhhhhhhhhhhhhhhhyso/:shs/-``````-+shhdmhy/.  `.hhy/```.-yhhhhhhhhdmh-::::/hhh");
        System.out.println("hhhhhhhhhhhhhhhhhhy/-...-hso:`````````-+sdmhh/````-hs+:.----oyhhhhhhhy::::::yhhh");
        System.out.println("hhhhhhhhyyhhhhhhhdso:````:hs+:````-.`   `-oyhy:..../so+/::::-:/oooo/::::::/shhhh");
        System.out.println("hhhhys+:--yhhhhhhmhs+-````oys+-`  `:/:.````.-+/:----os+++/::::::::::::::/oyhhhhh");
        System.out.println("hhhhs:```./hhhhhhdmys/.```.yys/.```.+so+/-------::::-yhssso++///:::::/+shhhhhhhh");
        System.out.println("hhdhy+-```.+hhhhhhdmys:````-hyy/....-yyysso/::::::::::dddhyyyyyssssyhdddhhhhhhhh");
        System.out.println("hhdmhy/-```-shhhhhhddys:``..:dys:----:dddhhyyo+/::::::/hddddddmdmmmmddhhhhhhhhhh");
        System.out.println("hhhdmys:.```-yhhhhhhmdhs:..-.odho::::-+hdmmdddhyo+/////shhhhdddddhhhhhhhhhhhhhhh");
        System.out.println("hhhhddyo:.```:hhhhhhdmdd+:-:-.mhh+::::-ohhddmmdddhysyyhhhhhhhhhhhhhhhhhhhhhhhhhh");
        System.out.println("hhhhhmhy+:....+hhhhhhdmd+::::.hmdh/::::-yhhhhddmmdddddhhhhhhhhhhhhhhhhhhhhhhhhhh");
        System.out.println("hhhhhdmhy+:---.+yhhhhhds-:::::hdmdh/:/++ohhhhhhhddhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        System.out.println("hhhhhhdms++:-----/+o+/:-:::::shhmddyyhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        System.out.println("hhhhhhhddoo+/::::----:::::/+yhhhdmdmmddhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        System.out.println("hhhhhhhhddysso//:::::://+shhhhhhhddhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        System.out.println("hhhhhhhhhdmdddhhyyyyyhhdmdhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        System.out.println("hhhhhhhhhhhdhhhhhhhhhhddhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        System.out.println("hhhhhhhhhhhhhhhdddhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        System.out.println("\n---------------------------------------"
                + "-----------------------------------------");

        System.out.println("--- MAIN MENU ---");
        System.out.println("Write 'n' to Start New Game.");
        System.out.println("Write 'q' to Quit.");

        System.out.println();

        System.out.print("Your choice: ");
        String userChoice = scan.nextLine();

        while (!userChoice.equals("q") && !userChoice.equals("n")) {
            System.out.print("Your choice: ");
            userChoice = scan.nextLine();
        }
        System.out.println("Hurray");

        if (userChoice.equals("n")) {
            Game newGame = new Game();
            newGame.startGame();
        }
    }
}
