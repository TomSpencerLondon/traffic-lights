package traffic.ui;

import java.util.LinkedList;
import java.util.Queue;

import static traffic.Main.clearConsole;

public class QueueThread extends Thread {
    private boolean running = true;
    private boolean printEnabled = false;
    boolean staticInfoPrinted = false;
    private int timeElapsed = 0;
    private final int maxRoads;

    private final Queue<String> roadQueue;
    private final int interval;

    public QueueThread(int maxRoads, int interval) {
        this.maxRoads = maxRoads;
        this.interval = interval;
        this.roadQueue = new LinkedList<>();
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
                    Printer.printSystemInfo(maxRoads, interval, this);
                }
            } catch (InterruptedException e) {
                running = false;
            }
        }
    }

    public synchronized void addRoad(String roadName) {
        if (queueIsFull()) {
            Printer.printQueueFull();
        } else {
            roadQueue.add(roadName);
            Printer.printAdd(roadName);
        }
    }

    public synchronized void deleteRoad() {
        if (roadQueue.isEmpty()) {
            Printer.printQueueIsEmpty();
        } else {
            String removedRoad = roadQueue.poll();
            Printer.printDeleted(removedRoad);
        }
    }

    private boolean queueIsFull() {
        return roadQueue.size() >= maxRoads;
    }

    public void stopThread() {
        interrupt();
    }

    public synchronized void enablePrinting() {
        printEnabled = true;
    }

    public synchronized void disablePrinting() {
        printEnabled = false;
    }

    public boolean isEmpty() {
        return roadQueue.isEmpty();
    }

    public Queue<String> roads() {
        return roadQueue;
    }
}

