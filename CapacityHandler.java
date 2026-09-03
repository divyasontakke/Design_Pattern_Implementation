
public class CapacityHandler
        extends EventHandler {

    @Override
    public void processEvent(
            EventRequest request) {

        System.out.println(
                "Checking capacity...");

        if (request.getCapacity() <= 0) {

            System.out.println(
                    "Event rejected: Invalid capacity.");

            return;
        }

        System.out.println(
                "Capacity validation passed.");

        if (nextHandler != null) {

            nextHandler.processEvent(request);
        }
    }
}
