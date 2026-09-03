public abstract class Event {

    protected String name;
    protected String venue;
    protected int capacity;

    public Event(
            String name,
            String venue,
            int capacity) {

        this.name = name;
        this.venue = venue;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public String getVenue() {
        return venue;
    }

    public int getCapacity() {
        return capacity;
    }

    public abstract void displayDetails();
}
