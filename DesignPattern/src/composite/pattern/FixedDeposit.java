package composite.pattern;
public class FixedDeposit implements AccountComponent{
	
	private double principal;
	private double interest;
	
	public FixedDeposit(double principal, double interest) {
		this.principal = principal;
		this.interest = interest;
	}

	@Override
	public double getBalance() {
		return principal + interest;
	}
	
}