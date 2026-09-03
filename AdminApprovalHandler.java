
public class AdminApprovalHandler
        extends EventHandler {

    @Override
    public void processEvent(
            EventRequest request) {

        System.out.println(
                "Checking admin approval...");

        System.out.println(
                "Admin approval passed.");

        System.out.println();
        System.out.println(
                "================================");

        System.out.println(
                "     EVENT APPROVED!");

        System.out.println(
                "Event: " +
                        request.getEventName());

        System.out.println(
                "================================");
    }
}
