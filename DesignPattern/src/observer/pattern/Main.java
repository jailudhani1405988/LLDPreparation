package observer.pattern;
public class Main {
    public static void main(String[] args) {

        StockPricePublisher publisher =
                new StockPricePublisher("AAPL", 200.0);

        publisher.registerObserver(new EmailAlertObserver("user@gmail.com"));
        publisher.registerObserver(new SMSAlertObserver("9876543210"));
        publisher.registerObserver(new TradingBotObserver());

        publisher.setPrice(180.0); // No notification
        publisher.setPrice(205.0); // All observers notified
    }
}