package composite.pattern;

public class CompositeDesignPattern {

	public static void main(String[] args) {
		SavingAccount sa = new SavingAccount(10000);
		FixedDeposit fd = new FixedDeposit(50000, 5000);

		Customer customer = new Customer();
		customer.add(sa);
		customer.add(fd);

		System.out.println(customer.getBalance()); // 65000
	}
}
