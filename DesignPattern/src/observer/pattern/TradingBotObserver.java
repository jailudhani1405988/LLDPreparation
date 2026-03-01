package observer.pattern;
public class TradingBotObserver implements PriceObserver {

    @Override
    public void update(String stockSymbol, double price) {
        System.out.println("Placing auto trade for " + stockSymbol +
                " at price " + price);
    }
}