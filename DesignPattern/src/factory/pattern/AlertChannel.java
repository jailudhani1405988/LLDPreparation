package factory.pattern;
public interface AlertChannel {
    String getType();     // unique key
    void send(String message);
}