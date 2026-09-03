import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        EventManager eventManager = EventManager.getInstance();

        while (true) {

            System.out.println();
            System.out.println("==============================================");
            System.out.println("     EVENT & FESTIVAL MANAGEMENT SYSTEM");
            System.out.println("==============================================");
            System.out.println("1. Create Event");
            System.out.println("2. View All Events");
            System.out.println("3. Register Participant");
            System.out.println("4. Send Event Notification");
            System.out.println("5. Validate Event");
            System.out.println("6. Admin Approve Event");
            System.out.println("7. Admin Reject Event");
            System.out.println("8. Delete Event");
            System.out.println("9. Exit");
            System.out.println("==============================================");

            System.out.print("Enter your choice: ");

            String input = scanner.nextLine();

            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Enter a number from 1 to 9.");
                continue;
            }

            switch (choice) {

                case 1:
                    createEvent(eventManager);
                    break;

                case 2:
                    eventManager.displayAllEvents();
                    break;

                case 3:
                    registerParticipant(eventManager);
                    break;

                case 4:
                    sendNotification(eventManager);
                    break;

                case 5:
                    validateEvent();
                    break;

                case 6:
                    adminApproveEvent(eventManager);
                 break;

                case 7:
                    adminRejectEvent(eventManager);
                    break;

                case 8:
                    deleteEvent(eventManager);
                    break;

                case 9:
                    System.out.println("Thank you for using the system!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // =========================================================
    // CREATE EVENT - FACTORY PATTERN
    // =========================================================

    public static void createEvent(EventManager eventManager) {

        System.out.println();
        System.out.println("========== CREATE EVENT ==========");

        System.out.println("1. Music Festival");
        System.out.println("2. Sports Event");
        System.out.println("3. Cultural Festival");

        System.out.print("Enter event type: ");

        int type;

        try {
            type = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid event type!");
            return;
        }

        String eventType;

        if (type == 1) {
            eventType = "music";
        } else if (type == 2) {
            eventType = "sports";
        } else if (type == 3) {
            eventType = "cultural";
        } else {
            System.out.println("Please choose 1, 2, or 3.");
            return;
        }

        System.out.print("Enter event name: ");
        String name = scanner.nextLine();

        if (name.trim().isEmpty()) {
            System.out.println("Event name cannot be empty.");
            return;
        }

        System.out.print("Enter venue: ");
        String venue = scanner.nextLine();

        if (venue.trim().isEmpty()) {
            System.out.println("Venue cannot be empty.");
            return;
        }

        System.out.print("Enter capacity: ");

        int capacity;

        try {
            capacity = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid capacity!");
            return;
        }

        if (capacity <= 0) {
            System.out.println("Capacity must be greater than 0.");
            return;
        }

        try {

            Event event = EventFactory.createEvent(
                    eventType,
                    name,
                    venue,
                    capacity);

            eventManager.addEvent(event);

            System.out.println();
            System.out.println("Event created successfully!");

            event.displayDetails();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }

    // =========================================================
    // REGISTER PARTICIPANT - OBSERVER PATTERN
    // =========================================================

    public static void registerParticipant(
            EventManager eventManager) {

        System.out.println();
        System.out.println("========== PARTICIPANT REGISTRATION ==========");

        if (eventManager.getEvents().isEmpty()) {

            System.out.println("No events available.");
            System.out.println("Please create an event first.");
            return;
        }

        System.out.println();
        System.out.println("Available Events:");

        for (int i = 0; i < eventManager.getEvents().size(); i++) {

            Event event = eventManager.getEvents().get(i);

            System.out.println(
                    (i + 1) + ". " +
                            event.getName() +
                            " - " +
                            event.getVenue());
        }

        System.out.println();

        System.out.print("Select event number: ");

        int eventNumber;

        try {
            eventNumber = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid event number!");
            return;
        }

        if (eventNumber < 1 ||
                eventNumber > eventManager.getEvents().size()) {

            System.out.println("Invalid event selection!");
            return;
        }

        Event selectedEvent = eventManager.getEvent(eventNumber - 1);

        System.out.println();
        System.out.println(
                "You selected: " +
                        selectedEvent.getName());

        System.out.print("Enter participant name: ");

        String participantName = scanner.nextLine();

        if (participantName.trim().isEmpty()) {

            System.out.println(
                    "Participant name cannot be empty.");

            return;
        }

        Participant participant = new Participant(participantName);

        EventNotifier notifier = eventManager.getNotifier(selectedEvent);

        if (notifier == null) {

            System.out.println(
                    "Error: Event notifier not found.");

            return;
        }

        notifier.addObserver(participant);

        System.out.println();
        System.out.println("==========================================");
        System.out.println("       REGISTRATION SUCCESSFUL!");
        System.out.println("Participant : " + participantName);
        System.out.println("Event       : " + selectedEvent.getName());
        System.out.println("==========================================");
    }

    // =========================================================
    // SEND NOTIFICATION - OBSERVER PATTERN
    // =========================================================

    public static void sendNotification(
            EventManager eventManager) {

        System.out.println();
        System.out.println("========== SEND EVENT NOTIFICATION ==========");

        if (eventManager.getEvents().isEmpty()) {

            System.out.println("No events available.");
            return;
        }

        for (int i = 0; i < eventManager.getEvents().size(); i++) {

            Event event = eventManager.getEvents().get(i);

            System.out.println(
                    (i + 1) + ". " +
                            event.getName());
        }

        System.out.println();

        System.out.print("Select event number: ");

        int eventNumber;

        try {
            eventNumber = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid event number!");
            return;
        }

        if (eventNumber < 1 ||
                eventNumber > eventManager.getEvents().size()) {

            System.out.println("Invalid event selection!");
            return;
        }

        Event selectedEvent = eventManager.getEvent(eventNumber - 1);

        System.out.print("Enter notification message: ");

        String message = scanner.nextLine();

        if (message.trim().isEmpty()) {

            System.out.println("Message cannot be empty.");
            return;
        }

        EventNotifier notifier = eventManager.getNotifier(selectedEvent);

        if (notifier == null) {

            System.out.println("Notifier not found!");
            return;
        }

        notifier.notifyObservers(message);
    }

    // =========================================================
    // VALIDATE EVENT - CHAIN OF RESPONSIBILITY
    // =========================================================

    public static void validateEvent() {

        System.out.println();
        System.out.println("========== EVENT VALIDATION ==========");

        System.out.print("Enter event name: ");
        String eventName = scanner.nextLine();

        if (eventName.trim().isEmpty()) {

            System.out.println(
                    "Event name cannot be empty.");

            return;
        }

        System.out.print("Enter venue: ");
        String venue = scanner.nextLine();

        if (venue.trim().isEmpty()) {

            System.out.println("Venue cannot be empty.");
            return;
        }

        System.out.print("Enter capacity: ");

        int capacity;

        try {
            capacity = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {

            System.out.println("Invalid capacity!");
            return;
        }

        System.out.print("Is safety approved? (yes/no): ");

        String safetyInput = scanner.nextLine();

        boolean safetyApproved = safetyInput.equalsIgnoreCase("yes");

        EventRequest request = new EventRequest(
                eventName,
                venue,
                capacity,
                safetyApproved);

        EventHandler venueHandler = new VenueHandler();

        EventHandler capacityHandler = new CapacityHandler();

        EventHandler safetyHandler = new SafetyHandler();

        EventHandler adminHandler = new AdminApprovalHandler();

        venueHandler.setNextHandler(capacityHandler);

        capacityHandler.setNextHandler(safetyHandler);

        safetyHandler.setNextHandler(adminHandler);

        System.out.println();

        venueHandler.processEvent(request);
    }

    // =========================================================
    // ADMIN APPROVE - PROXY PATTERN
    // =========================================================

    public static void adminApproveEvent(EventManager eventManager) {

        System.out.println();
        System.out.println("========== ADMIN APPROVAL ==========");

        System.out.print("Enter Admin username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Admin password: ");
        String password = scanner.nextLine();

        if (!username.equals("admin") ||
                !password.equals("1234")) {

            System.out.println();
            System.out.println("ACCESS DENIED!");
            System.out.println("Invalid Admin credentials.");

            return;
        }

        System.out.print("Enter event name: ");
        String eventName = scanner.nextLine();

        if (eventName.trim().isEmpty()) {

            System.out.println(
                    "Event name cannot be empty.");

            return;
        }

        EventService service = new EventServiceProxy("ADMIN", eventManager);

        service.approveEvent(eventName);
    }

    // =========================================================
    // ADMIN REJECT - PROXY PATTERN
    // =========================================================

    public static void adminRejectEvent(EventManager eventManager) {

        System.out.println();
        System.out.println("========== ADMIN REJECTION ==========");

        System.out.print("Enter Admin username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Admin password: ");
        String password = scanner.nextLine();

        if (!username.equals("admin") ||
                !password.equals("1234")) {

            System.out.println();
            System.out.println("ACCESS DENIED!");
            System.out.println("Invalid Admin credentials.");

            return;
        }

        System.out.print("Enter event name: ");
        String eventName = scanner.nextLine();

        if (eventName.trim().isEmpty()) {

            System.out.println(
                    "Event name cannot be empty.");

            return;
        }

        EventService service = new EventServiceProxy("ADMIN", eventManager);
        service.rejectEvent(eventName);
    }

    // =========================================================
    // DELETE EVENT - PROXY PATTERN
    // =========================================================
    
    public static void deleteEvent(EventManager eventManager) {

    System.out.println();
    System.out.println("========== DELETE EVENT ==========");

    System.out.print("Enter user role (ADMIN/PARTICIPANT): ");
    String role = scanner.nextLine();

    System.out.print("Enter event name: ");
    String eventName = scanner.nextLine();

    if (eventName.trim().isEmpty()) {
        System.out.println("Event name cannot be empty.");
        return;
    }

    EventService service = new EventServiceProxy(role, eventManager);

    service.deleteEvent(eventName);
}
}
