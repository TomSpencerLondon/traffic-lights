package traffic.ui;

import static traffic.Main.clearConsole;

public class QueueThread extends Thread {
    private boolean running = true;
    private boolean printEnabled = false;
    boolean staticInfoPrinted = false;
    private int timeElapsed = 0;
    private final int numberOfRoads;
    private final int interval;

    public QueueThread(int numberOfRoads, int interval) {
        this.numberOfRoads = numberOfRoads;
        this.interval = interval;
        setName("QueueThread");
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1000);
                timeElapsed++;

                if (printEnabled) {
                    clearConsole();
                    System.out.println("! " + timeElapsed + "s. have passed since system startup !");
                    Printer.printSystemStaticInfo(numberOfRoads, interval);
                }
            } catch (InterruptedException e) {
                running = false;
            }
        }
    }


    public synchronized void enablePrinting() {
        printEnabled = true;
    }

    public synchronized void disablePrinting() {
        printEnabled = false;
    }

    public void stopThread() {
        interrupt();
    }
}

