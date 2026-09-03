import java.util.ArrayList;
import java.util.List;

public class EventNotifier {

    private Event event;

    private List<Observer> observers;

    public EventNotifier(Event event) {

        this.event = event;

        observers = new ArrayList<>();
    }

    public Event getEvent() {

        return event;
    }

    public void addObserver(
            Observer observer) {

        if (!observers.contains(observer)) {

            observers.add(observer);

            System.out.println(
                    observer.getName() +
                            " registered for " +
                            event.getName());

        } else {

            System.out.println(
                    "Participant is already registered.");
        }
    }

    public void removeObserver(
            Observer observer) {

        observers.remove(observer);

        System.out.println(
                observer.getName() +
                        " registration cancelled.");
    }

    public void notifyObservers(
            String message) {

        System.out.println();

        System.out.println(
                "Notification for event: " +
                        event.getName());

        if (observers.isEmpty()) {

            System.out.println(
                    "No participants registered for this event.");

            return;
        }

        for (Observer observer : observers) {

            observer.update(message);
        }
    }
}
