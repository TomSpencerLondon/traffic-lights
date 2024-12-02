package traffic.ui;

import java.util.Scanner;

public enum ApplicationState {
    MAIN_MENU, EXIT, SYSTEM_INFO;

    public static ApplicationState getApplicationState(int choice, QueueThread queueThread, InputHandler inputHandler,
                                                       InputHandler closeHandler) {
        return switch (choice) {
            case 1 -> {
                Printer.printStubMessage("add");
                yield MAIN_MENU;
            }
            case 2 -> {
                Printer.printStubMessage("delete");
                yield MAIN_MENU;
            }
            case 3 -> {
                queueThread.enablePrinting();
                inputHandler.handle();
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

