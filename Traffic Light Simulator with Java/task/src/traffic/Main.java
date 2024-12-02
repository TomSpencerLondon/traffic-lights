package traffic;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Printer.printWelcomeMessage();

        System.out.print("Input the number of roads: ");
        int numberOfRoads = scanner.nextInt();

        System.out.print("Input the interval: ");
        int interval = scanner.nextInt();

        boolean continueProgram = true;
        while (continueProgram) {
            Printer.printMenu();

            int choice = scanner.nextInt();

            continueProgram = MenuHandler.handleMenuChoice(choice);
        }

        scanner.close();
    }
}
