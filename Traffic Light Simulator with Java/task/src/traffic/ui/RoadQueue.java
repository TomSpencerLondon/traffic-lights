package traffic.ui;

public class RoadQueue {
    private final String[] roads;
    private int size = 0;
    private int front = 0;
    private int rear = -1;
    private int interval;

    public RoadQueue(int capacity, int interval) {
        this.roads = new String[capacity];
        this.interval = interval;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == roads.length;
    }

    public void add(String roadName) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }

        rear = (rear + 1) % roads.length;
    }

}
