
public class EventFactory {

    public static Event createEvent(
            String type,
            String name,
            String venue,
            int capacity) {

        if (type.equalsIgnoreCase("music")) {

            return new MusicFestival(
                    name,
                    venue,
                    capacity);

        } else if (type.equalsIgnoreCase("sports")) {

            return new SportsEvent(
                    name,
                    venue,
                    capacity);

        } else if (type.equalsIgnoreCase("cultural")) {

            return new CulturalFestival(
                    name,
                    venue,
                    capacity);

        } else {

            throw new IllegalArgumentException(
                    "Invalid event type!");
        }
    }
}
