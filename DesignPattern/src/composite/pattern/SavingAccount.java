package composite.pattern;

public class SavingAccount implements AccountComponent{
	
	private double balance;
	
	public SavingAccount(double balance) {
		this.balance = balance;
	}

	@Override
	public double getBalance() {
		return balance;
	}
	
}