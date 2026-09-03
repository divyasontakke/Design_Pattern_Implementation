public class RealEventService implements EventService {

    private EventManager eventManager;

    public RealEventService(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    @Override
    public void deleteEvent(String eventName) {

        eventManager.deleteEvent(eventName);
    }

    @Override
    public void approveEvent(String eventName) {

        System.out.println(
                "Event '" +
                eventName +
                "' approved successfully."
        );
    }

    @Override
    public void rejectEvent(String eventName) {

        System.out.println(
                "Event '" +
                eventName +
                "' rejected successfully."
        );
    }
}