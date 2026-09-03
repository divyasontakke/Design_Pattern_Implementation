public class Participant implements Observer {

    private String name;

    public Participant(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    @Override
    public void update(String message) {

        System.out.println(
                "Notification for " +
                        name +
                        ": " +
                        message);
    }
}
