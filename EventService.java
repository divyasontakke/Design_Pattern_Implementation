public interface EventService {

    void approveEvent(String eventName);

    void rejectEvent(String eventName);

    void deleteEvent(String eventName);
}
