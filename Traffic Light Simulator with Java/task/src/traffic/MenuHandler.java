package traffic;

public class MenuHandler {
    public static boolean handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                Printer.printRoadAdded();
                // Add logic for adding a road here
                break;
            case 2:
                Printer.printRoadDeleted();
                // Add logic for deleting a road here
                break;
            case 3:
                Printer.printSystemOpened();
                // Add logic for opening the system here
                break;
            case 0:
                Printer.printGoodbyeMessage();
                return false; // Return false to indicate the program should exit
            default:
                Printer.printInvalidOptionMessage();
        }
        return true; // Continue the loop
    }
}

