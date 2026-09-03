
public class CulturalFestival extends Event {

    public CulturalFestival(
            String name,
            String venue,
            int capacity) {

        super(name, venue, capacity);
    }

    @Override
    public void displayDetails() {

        System.out.println("Type     : Cultural Festival");
        System.out.println("Name     : " + name);
        System.out.println("Venue    : " + venue);
        System.out.println("Capacity : " + capacity);
    }
}
