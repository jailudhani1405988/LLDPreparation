package observer.pattern;
public class SMSAlertObserver implements PriceObserver {

    private final String phoneNumber;

    public SMSAlertObserver(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void update(String stockSymbol, double price) {
        System.out.println("SMS sent to " + phoneNumber +
                ": " + stockSymbol + " reached price " + price);
    }
}