package traffic.ui;

class Road {
    private String name;
    private boolean isOpen;
    private int timeRemaining;

    public Road(String name, int timeRemaining) {
        this.name = name;
        this.isOpen = false;
        this.timeRemaining = timeRemaining;
    }

    public String getName() {
        return name;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public void decrementTime() {
        if (timeRemaining > 0) {
            timeRemaining--;
        }
    }
}
