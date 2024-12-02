package traffic.ui;

public enum ApplicationState {
    MAIN_MENU, EXIT, SYSTEM_INFO;

    public static ApplicationState getApplicationState(int choice, QueueThread queueThread, ScannerInput scannerInput,
                                                       ScannerCommand closeHandler) {
        return switch (choice) {
            case 1 -> {
                Printer.printStubMessage("input: ");
                String roadName = scannerInput.handle();
                queueThread.addRoad(roadName);
                yield MAIN_MENU;
            }
            case 2 -> {
                queueThread.deleteRoad();
                yield MAIN_MENU;
            }
            case 3 -> {
                queueThread.enablePrinting();
                scannerInput.handle();
                queueThread.disablePrinting();
                yield SYSTEM_INFO;
            }
            case 0 -> {
                Printer.printGoodbyeMessage();
                queueThread.stopThread();
                closeHandler.handle();
                yield EXIT;
            }
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        };
    }
}

