
public class VenueHandler
        extends EventHandler {

    @Override
    public void processEvent(
            EventRequest request) {

        System.out.println(
                "Checking venue...");

        if (request.getVenue() == null ||
                request.getVenue().isEmpty()) {

            System.out.println(
                    "Event rejected: Venue missing.");

            return;
        }

        System.out.println(
                "Venue validation passed.");

        if (nextHandler != null) {

            nextHandler.processEvent(request);
        }
    }
}
