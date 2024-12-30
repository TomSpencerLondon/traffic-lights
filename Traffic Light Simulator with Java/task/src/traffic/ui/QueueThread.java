package traffic.ui;

import java.util.*;

import static traffic.Main.clearConsole;

public class QueueThread extends Thread {
    private final Deque<Road> roads;
    private final List<Road> displayOrder;  // New field for display order

    private boolean running = true;
    private boolean printEnabled = false;
    private int timeElapsed = 0;
    private final int maxRoads;

    private final RoadQueue roadQueue;
    private final int interval;

    public QueueThread(int maxRoads, int interval) {
        this.maxRoads = maxRoads;
        this.interval = interval;
        this.roadQueue = new RoadQueue(maxRoads, interval);
        this.roads = new ArrayDeque<>();
        this.displayOrder = new ArrayList<>();
        setName("QueueThread");
    }

    public synchronized void addRoad(String roadName) {
        if (queueIsFull()) {
            Printer.printQueueFull();
        } else {
            Road newRoad = new Road(roadName, interval);
            roads.addFirst(newRoad);  // Add to end
            displayOrder.add(newRoad);
            roadQueue.add(roadName);

            if (roads.size() == 1) {
                newRoad.setOpen(true);
            }

            Printer.printAdd(roadName);
        }
    }

    public synchronized void deleteRoad() {
        if (roads.isEmpty()) {
            Printer.printQueueIsEmpty();
        } else {
            Road removedRoad = roads.removeLast();  // Remove from front
            displayOrder.remove(removedRoad);
            roadQueue.remove();
            Printer.printDeleted(removedRoad.getName());

            if (!roads.isEmpty()) {
                Road nextRoad = roads.peekLast();
                nextRoad.setOpen(true);
                nextRoad.setTimeRemaining(interval);
            }
        }
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1000);
                timeElapsed++;

                synchronized (roads) {
                    for(Road road : roads) {
                        road.decrementTime();
                    }
                }

                Road frontRoad = roads.peekLast();

                if (frontRoad != null && frontRoad.getTimeRemaining() <= 0) {
                    roads.removeLast();
                    frontRoad.setOpen(false);
                    frontRoad.setTimeRemaining(interval * roads.size());
                    roads.addFirst(frontRoad);  // Keep using add() for roads

                    // Update roadQueue in same order
                    roadQueue.remove();
                    roadQueue.add(frontRoad.getName());

                    Road nextRoad = roads.peekLast();

                    if (nextRoad != null) {
                        nextRoad.setOpen(true);
                        nextRoad.setTimeRemaining(interval);
                    }
                }

                if (printEnabled) {
                    clearConsole();
                    Printer.printSystemInfo(maxRoads, displayOrder, interval, timeElapsed);
                }
            } catch (InterruptedException e) {
                running = false;
            }
        }
    }

    private boolean queueIsFull() {
        return roads.size() >= maxRoads;
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

    public List<Road> roads() {
        return displayOrder;
    }

    public RoadQueue getRoadQueue() {
        return roadQueue;
    }

    public synchronized String[] getRoadNames() {
        return roadQueue.getNames();
    }
}

