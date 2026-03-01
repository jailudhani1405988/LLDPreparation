package observer.pattern;
public class EmailAlertObserver implements PriceObserver {

    private final String email;

    public EmailAlertObserver(String email) {
        this.email = email;
    }

    @Override
    public void update(String stockSymbol, double price) {
        System.out.println("Email sent to " + email +
                ": " + stockSymbol + " reached price " + price);
    }
}