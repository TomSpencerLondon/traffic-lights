package traffic;

import traffic.ui.Printer;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Print welcome message
        Printer.printWelcomeMessage();

        // Input number of roads
        int numberOfRoads = getPositiveIntegerInput(scanner, "Input the number of roads: ");

        // Input interval
        int interval = getPositiveIntegerInput(scanner, "Input the interval: ");

        // Menu loop
        while (true) {
            clearConsole(); // Clear the console before showing the menu
            waitForEnter(scanner);
            Printer.printMenu(); // Print menu

            int choice = getValidMenuChoice(scanner); // Get valid menu choice

            // Handle the user's choice
            switch (choice) {
                case 1:
                    Printer.printStubMessage("add"); // Print one-line stub
                    break;
                case 2:
                    Printer.printStubMessage("delete"); // Print one-line stub
                    break;
                case 3:
                    Printer.printStubMessage("system"); // Print one-line stub
                    break;
                case 0:
                    Printer.printGoodbyeMessage();
                    scanner.close();
                    return; // Exit program
            }

            // Wait for user to press Enter before the next iteration
            clearConsole();
        }
    }

    private static void clearConsole() {
        try {
            var clearCommand = System.getProperty("os.name").contains("Windows")
                    ? new ProcessBuilder("cmd", "/c", "cls")
                    : new ProcessBuilder("clear");
            clearCommand.inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            // Handle exception (if any) silently
        }
    }

    private static int getPositiveIntegerInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                if (value > 0) {
                    return value;
                }
            }
            System.out.print("Incorrect input. Try again: ");
            scanner.nextLine(); // Clear invalid input
        }
    }

    private static int getValidMenuChoice(Scanner scanner) {
        String input = scanner.nextLine();
        int choice = -1;
        try {
            choice = Integer.parseInt(input); // Attempt to parse input as an integer
            if (choice >= 0 && choice <= 3) {
                return choice; // Return valid menu choice
            } else {
                System.out.println("incorrect option");
            }
        } catch (NumberFormatException e) {
            // Input was not a valid integer, fall through to print error
            System.out.println("incorrect option");
        }

        return choice;
    }

    private static void waitForEnter(Scanner scanner) {
        scanner.nextLine();
    }
}
