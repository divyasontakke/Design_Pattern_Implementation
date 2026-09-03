
public class SafetyHandler
        extends EventHandler {

    @Override
    public void processEvent(
            EventRequest request) {

        System.out.println(
                "Checking safety...");

        if (!request.isSafetyApproved()) {

            System.out.println(
                    "Event rejected: Safety not approved.");

            return;
        }

        System.out.println(
                "Safety validation passed.");

        if (nextHandler != null) {

            nextHandler.processEvent(request);
        }
    }
}
