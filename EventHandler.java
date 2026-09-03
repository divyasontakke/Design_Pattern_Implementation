
public abstract class EventHandler {

    protected EventHandler nextHandler;

    public void setNextHandler(
            EventHandler nextHandler) {

        this.nextHandler = nextHandler;
    }

    public abstract void processEvent(
            EventRequest request);
}
