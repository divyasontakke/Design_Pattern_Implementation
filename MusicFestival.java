
public class MusicFestival extends Event {

    public MusicFestival(
            String name,
            String venue,
            int capacity) {

        super(name, venue, capacity);
    }

    @Override
    public void displayDetails() {

        System.out.println("Type     : Music Festival");
        System.out.println("Name     : " + name);
        System.out.println("Venue    : " + venue);
        System.out.println("Capacity : " + capacity);
    }
}
