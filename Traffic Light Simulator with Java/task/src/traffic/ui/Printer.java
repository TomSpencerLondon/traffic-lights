package traffic.ui;

public class Printer {
    public static void printWelcomeMessage() {
        System.out.println("Welcome to the traffic management system!");
    }

    // Method to print the menu options
    public static void printMenu() {
        System.out.println("Menu:");
        System.out.println("1. Add road");
        System.out.println("2. Delete road");
        System.out.println("3. Open system");
        System.out.println("0. Quit");
    }

    public static void printStubMessage(String input) {
        System.out.println(input);
    }

    public static void printGoodbyeMessage() {
        System.out.println("Bye!");
    }

    public static void printSystemStaticInfo(int numberOfRoads, int interval) {
        System.out.println("! Number of roads: " + numberOfRoads + " !");
        System.out.println("! Interval: " + interval + " !");
        System.out.println("! Press \"Enter\" to open menu !");
    }

    public static void printTimeElapsed(int timeElapsed) {
        System.out.print(timeElapsed);
    }
}
