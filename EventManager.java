import java.util.ArrayList;
import java.util.List;

public class EventManager {

    // =========================================================
    // SINGLETON PATTERN
    // =========================================================

    private static EventManager instance;

    private List<Event> events;
    private List<EventNotifier> notifiers;

    // Private constructor prevents creating objects from outside
    private EventManager() {
        events = new ArrayList<>();
        notifiers = new ArrayList<>();
    }

    // Returns the single EventManager object
    public static EventManager getInstance() {

        if (instance == null) {
            instance = new EventManager();
        }

        return instance;
    }

    // =========================================================
    // ADD EVENT
    // =========================================================

    public void addEvent(Event event) {

        if (event == null) {
            System.out.println("Cannot add a null event.");
            return;
        }

        events.add(event);

        // Create a notifier for this event
        EventNotifier notifier = new EventNotifier(event);
        notifiers.add(notifier);
    }

    // =========================================================
    // DISPLAY ALL EVENTS
    // =========================================================

    public void displayAllEvents() {

        System.out.println();
        System.out.println("========== ALL EVENTS ==========");

        if (events.isEmpty()) {
            System.out.println("No events available.");
            return;
        }

        for (int i = 0; i < events.size(); i++) {

            System.out.println();
            System.out.println("Event " + (i + 1));
            System.out.println("------------------------------");

            events.get(i).displayDetails();
        }
    }

    // =========================================================
    // GET ALL EVENTS
    // =========================================================

    public List<Event> getEvents() {
        return events;
    }

    // =========================================================
    // GET EVENT BY INDEX
    // =========================================================

    public Event getEvent(int index) {

        if (index < 0 || index >= events.size()) {
            return null;
        }

        return events.get(index);
    }

    // =========================================================
    // GET NOTIFIER FOR AN EVENT
    // OBSERVER PATTERN
    // =========================================================

    public EventNotifier getNotifier(Event event) {

        int index = events.indexOf(event);

        if (index >= 0 && index < notifiers.size()) {
            return notifiers.get(index);
        }

        return null;
    }

    // =========================================================
    // DELETE EVENT
    // =========================================================

    public void deleteEvent(String eventName) {

        if (eventName == null || eventName.trim().isEmpty()) {

            System.out.println("Event name cannot be empty.");
            return;
        }

        for (int i = 0; i < events.size(); i++) {

            Event event = events.get(i);

            if (event.getName().equalsIgnoreCase(eventName)) {

                // Remove event
                events.remove(i);

                // Remove corresponding notifier
                if (i < notifiers.size()) {
                    notifiers.remove(i);
                }

                System.out.println();
                System.out.println(
                        "Event '" + eventName +
                        "' deleted successfully."
                );

                return;
            }
        }

        System.out.println();
        System.out.println(
                "Event '" + eventName +
                "' not found."
        );
    }
}