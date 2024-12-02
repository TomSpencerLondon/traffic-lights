package traffic;

import traffic.ui.InvalidChoiceException;
import traffic.ui.Printer;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Printer.printWelcomeMessage();

        int numberOfRoads = getPositiveIntegerInput(scanner, "Input the number of roads: ");

        int interval = getPositiveIntegerInput(scanner, "Input the interval: ");

        while (true) {
            clearConsole();
            waitForEnter(scanner);
            Printer.printMenu();

            try {
                int choice = getValidMenuChoice(scanner);
                switch (choice) {
                    case 1 -> Printer.printStubMessage("add");
                    case 2 -> Printer.printStubMessage("delete");
                    case 3 -> Printer.printStubMessage("system");
                    case 0 -> {
                        Printer.printGoodbyeMessage();
                        scanner.close();
                        return;
                    }
                }
            } catch (InvalidChoiceException e) {
                Printer.printStubMessage("incorrect option");
            }

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

    private static int getValidMenuChoice(Scanner scanner) throws InvalidChoiceException {
        String input = scanner.nextLine(); // Read the entire input as a string
        try {
            int choice = Integer.parseInt(input); // Attempt to parse input as an integer
            if (choice >= 0 && choice <= 3) {
                return choice; // Return valid menu choice
            }
        } catch (NumberFormatException e) {
            // Input was not a valid integer
        }
        throw new InvalidChoiceException("incorrect option"); // Throw exception for invalid input
    }


    private static void waitForEnter(Scanner scanner) {
        scanner.nextLine();
    }
}
