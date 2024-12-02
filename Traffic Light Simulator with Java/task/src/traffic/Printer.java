package traffic;

public class Printer {
    static void printWelcomeMessage() {
        System.out.println("Welcome to the traffic management system!");
    }

    // Method to print the menu options
    static void printMenu() {
        System.out.println("Menu:");
        System.out.println("1. Add");
        System.out.println("2. Delete");
        System.out.println("3. System");
        System.out.println("0. Quit");
    }

    static void promptNumberOfRoads() {
        System.out.print("Input the number of roads: ");
    }

    static void promptInterval() {
        System.out.print("Input the interval: ");
    }

    static void printRoadAdded() {
        System.out.println("Road added");
    }

    static void printRoadDeleted() {
        System.out.println("Road deleted");
    }

    static void printSystemOpened() {
        System.out.println("System opened");
    }

    static void printGoodbyeMessage() {
        System.out.println("Bye!");
    }

    static void printInvalidOptionMessage() {
        System.out.println("Invalid option. Please select a valid menu option.");
    }
}
