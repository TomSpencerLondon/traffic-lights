package traffic.ui;

import java.util.LinkedList;
import java.util.Queue;
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

    public static void printSystemInfo(int maxRoads, Queue<Road> roads, int interval) {
        LinkedList<Road> printable = new LinkedList<>(roads);
        System.out.println("! Number of roads: " + maxRoads + " !");
        System.out.println("! Interval: " + interval + " !");

        while(printable.peek() != null) {
            Road road = printable.poll();
            String state = road.isOpen() ? "\u001B[32mopen\u001B[0m" : "\u001B[31mclosed\u001B[0m";
            System.out.printf("Road \"%s\" is %s for %ds.\n", road.getName(), state, road.getTimeRemaining());
        }

        System.out.println("! Press \"Enter\" to open menu !");
    }

    public static void printQueueFull() {
        System.out.println("queue is full");
    }

    public static void printAdd(String roadName) {
        System.out.println("add " + roadName);
    }

    public static void printQueueIsEmpty() {
        System.out.println("queue is empty");
    }

    public static void printDeleted(String removedRoad) {
        System.out.println("delete " + removedRoad);
    }
}
