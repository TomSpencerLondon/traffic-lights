package traffic;

import traffic.ui.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        Printer.printWelcomeMessage();

        int numberOfRoads = getPositiveIntegerInput(scanner, "Input the number of roads: ");

        int interval = getPositiveIntegerInput(scanner, "Input the interval: ");

        QueueThread queueThread = new QueueThread(numberOfRoads, interval);
        queueThread.start();
        ApplicationState state = ApplicationState.MAIN_MENU;
        while (state != ApplicationState.EXIT) {
            printFor(state, scanner::nextLine);
            state = menuChoice(queueThread, scanner);
        }
    }

    private static ApplicationState menuChoice(QueueThread queueThread, Scanner scanner) {
        try {
            int choice = getValidMenuChoice(scanner);
            return ApplicationState.getApplicationState(
                    choice,
                    queueThread,
                    scanner::nextLine,
                    scanner::close);
        } catch (InvalidChoiceException e) {
            Printer.printStubMessage("incorrect option");
            return ApplicationState.MAIN_MENU;
        }
    }

    private static void printFor(ApplicationState state, InputHandler inputHandler) {
        if (state == ApplicationState.MAIN_MENU) {
            clearConsole();
            inputHandler.handle();
            Printer.printMenu();
        } else if (state == ApplicationState.SYSTEM_INFO) {
            clearConsole();
            Printer.printMenu();
        }
    }


    public static void clearConsole() {
        try {
            var clearCommand = System.getProperty("os.name").contains("Windows")
                    ? new ProcessBuilder("cmd", "/c", "cls")
                    : new ProcessBuilder("clear");
            clearCommand.inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
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
            scanner.nextLine();
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


    public static void waitForEnter(Scanner scanner) {
        scanner.nextLine();
    }
}
