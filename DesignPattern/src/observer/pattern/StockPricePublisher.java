package observer.pattern;
import java.util.ArrayList;
import java.util.List;

public class StockPricePublisher implements StockPriceSubject {

    private final String stockSymbol;
    private final double alertPrice;
    private final List<PriceObserver> observers = new ArrayList<>();

    private double currentPrice;

    public StockPricePublisher(String stockSymbol, double alertPrice) {
        this.stockSymbol = stockSymbol;
        this.alertPrice = alertPrice;
    }

    public void setPrice(double newPrice) {
        this.currentPrice = newPrice;

        if (currentPrice >= alertPrice) {
            notifyObservers();
        }
    }

    @Override
    public void registerObserver(PriceObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(PriceObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (PriceObserver observer : observers) {
            observer.update(stockSymbol, currentPrice);
        }
    }
}