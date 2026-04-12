package factory.pattern;
@Component
public class SmsAlert implements AlertChannel {

    @Override
    public String getType() {
        return "SMS";
    }

    @Override
    public void send(String message) {
        // send sms
    }
}