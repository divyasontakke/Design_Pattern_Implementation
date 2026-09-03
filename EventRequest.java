
public class EventRequest {

    private String eventName;

    private String venue;

    private int capacity;

    private boolean safetyApproved;

    public EventRequest(
            String eventName,
            String venue,
            int capacity,
            boolean safetyApproved) {

        this.eventName = eventName;
        this.venue = venue;
        this.capacity = capacity;
        this.safetyApproved = safetyApproved;
    }

    public String getEventName() {
        return eventName;
    }

    public String getVenue() {
        return venue;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isSafetyApproved() {
        return safetyApproved;
    }
}
