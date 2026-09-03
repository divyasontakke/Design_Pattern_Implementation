public class EventServiceProxy implements EventService {

    private EventService realService;
    private String role;

    public EventServiceProxy(String role, EventManager eventManager) {
        this.role = role;
        this.realService = new RealEventService(eventManager);
    }

    @Override
    public void approveEvent(String eventName) {

        if (role.equalsIgnoreCase("ADMIN")) {

            System.out.println("Admin authorization successful.");

            realService.approveEvent(eventName);

        } else {

            System.out.println("ACCESS DENIED!");
            System.out.println("Only Admin can approve events.");
        }
    }

    @Override
    public void rejectEvent(String eventName) {

        if (role.equalsIgnoreCase("ADMIN")) {

            System.out.println("Admin authorization successful.");

            realService.rejectEvent(eventName);

        } else {

            System.out.println("ACCESS DENIED!");
            System.out.println("Only Admin can reject events.");
        }
    }

    @Override
    public void deleteEvent(String eventName) {

        if (role.equalsIgnoreCase("ADMIN")) {

            System.out.println("Admin authorization successful.");

            realService.deleteEvent(eventName);

        } else {

            System.out.println("ACCESS DENIED!");
            System.out.println("Only Admin can delete events.");
        }
    }
}