package factory.pattern;
@Component
public class EmailAlert implements AlertChannel {

    @Override
    public String getType() {
        return "EMAIL";
    }

    @Override
    public void send(String message) {
        // send Email
    }
}