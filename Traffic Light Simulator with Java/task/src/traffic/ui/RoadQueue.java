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
        roads[rear] = roadName;
        size++;
    }

    public String remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        String removed = roads[front];
        roads[front] = null;
        front = (front + 1) % roads.length;
        size--;

        return removed;
    }

    public String peek() {
        if (isEmpty()) {
            return null;
        }
        return roads[front];
    }

    public int size() {
        return size;
    }

    public synchronized String[] getNames() {
        String[] result = new String[size];
        // Fill array in normal order
        for (int i = 0; i < size; i++) {
            result[i] = roads[(front + i) % roads.length];
        }
        return result;
    }
}
