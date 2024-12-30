package traffic.ui;

import java.util.*;
import java.util.stream.Collectors;

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

    public static void printSystemInfo(int maxRoads, List<Road> roads, int interval, int elapsedTime) {
        System.out.println(elapsedTime + "s have passed since system startup");

        // Print number of roads and interval
        System.out.println("Number of roads: " + maxRoads);
        System.out.println("Interval: " + interval);

        // Print queue details
        if (!roads.isEmpty()) {
            // Print in original order (front to rear)
            for (int i = roads.size() - 1; i >= 0; i--) {
                System.out.println(roads.get(i).getName());
            }
        }

        // Print prompt for menu
        System.out.println("Press \"Enter\" to open menu");
    }


    public static void printQueueFull() {
        System.out.println("queue is full");
    }

    public static void printAdd(String roadName) {
        System.out.println(roadName + " added!");
    }

    public static void printQueueIsEmpty() {
        System.out.println("queue is empty");
    }

    public static void printDeleted(String removedRoad) {
        System.out.println(removedRoad + " deleted!");
    }
}
