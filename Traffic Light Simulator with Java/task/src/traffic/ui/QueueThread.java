package traffic.ui;

import java.util.LinkedList;
import java.util.Queue;

import static traffic.Main.clearConsole;

public class QueueThread extends Thread {
    private boolean running = true;
    private boolean printEnabled = false;
    private int timeElapsed = 0;
    private final int maxRoads;

    private final Queue<Road> roadQueue;
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


                synchronized (roadQueue) {
                    for(Road road : roadQueue) {
                        road.decrementTime();
                    }
                }

                Road frontRoad = roadQueue.peek();

                if (frontRoad != null && frontRoad.getTimeRemaining() <= 0) {
                    roadQueue.poll();
                    frontRoad.setOpen(false);
                    frontRoad.setTimeRemaining(interval * roadQueue.size());
                    roadQueue.add(frontRoad);

                    Road nextRoad = roadQueue.peek();

                    if (nextRoad != null) {
                        nextRoad.setOpen(true);
                        nextRoad.setTimeRemaining(interval);
                    }
                }

                if (printEnabled) {
                    clearConsole();
                    System.out.println("! " + timeElapsed + "s. have passed since system startup !");
                    Printer.printSystemInfo(maxRoads, roads(), interval);
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
            Road newRoad = new Road(roadName, interval);
            roadQueue.add(newRoad);

            if (roadQueue.size() == 1) {
                newRoad.setOpen(true);
            }

            Printer.printAdd(roadName);
        }
    }

    public synchronized void deleteRoad() {
        if (roadQueue.isEmpty()) {
            Printer.printQueueIsEmpty();
        } else {
            Road removedRoad = roadQueue.poll();
            Printer.printDeleted(removedRoad.getName());

            if (!roadQueue.isEmpty()) {
                Road nextRoad = roadQueue.peek();
                nextRoad.setOpen(true);
                nextRoad.setTimeRemaining(interval);
            }
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

    public Queue<Road> roads() {
        return roadQueue;
    }
}

