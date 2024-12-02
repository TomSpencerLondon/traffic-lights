package traffic;

import traffic.ui.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Printer.printWelcomeMessage();

        int numberOfRoads = getPositiveIntegerInput(scanner, "Input the number of roads: ");

        int interval = getPositiveIntegerInput(scanner, "Input the interval: ");

        QueueThread queueThread = new QueueThread(numberOfRoads, interval);
        queueThread.start();
        runTrafficManagementSystem(queueThread, scanner);
    }

    private static void runTrafficManagementSystem(QueueThread queueThread, Scanner scanner) {
        ApplicationState state = ApplicationState.MAIN_MENU;

        while (state != ApplicationState.EXIT) {
            handle(state, scanner::nextLine);
            state = processMenuChoice(queueThread, scanner);
        }
    }

    private static void handle(ApplicationState state, ScannerCommand scannerCommand) {
        clearConsole();
        if (state == ApplicationState.MAIN_MENU) {
            waitForEnter(scannerCommand);
            Printer.printMenu();
        } else if (state == ApplicationState.SYSTEM_INFO) {
            Printer.printMenu();
        }
    }

    private static void waitForEnter(ScannerCommand scannerCommand) {
        scannerCommand.handle();
    }

    private static ApplicationState processMenuChoice(QueueThread queueThread, Scanner scanner) {
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


    public static void clearConsole() {
        try {
            var clearCommand = System.getProperty("os.name").contains("Windows")
                    ? new ProcessBuilder("cmd", "/c", "cls")
                    : new ProcessBuilder("clear");
            clearCommand.inheritIO().start().waitFor();
        } catch (IOException | InterruptedException ignored) {
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
        String input = scanner.nextLine();
        try {
            int choice = Integer.parseInt(input);
            if (isInTheMenu(choice)) {
                return choice;
            }
        } catch (NumberFormatException e) {

        }
        throw new InvalidChoiceException("incorrect option");
    }

    private static boolean isInTheMenu(int choice) {
        return choice >= 0 && choice <= 3;
    }
}
