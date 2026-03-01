package observer.pattern;

public interface StockPriceSubject {
	
	  void registerObserver(PriceObserver priceObserver);
	  void removeObserver(PriceObserver priceObserver);
	  void notifyObservers();

}
