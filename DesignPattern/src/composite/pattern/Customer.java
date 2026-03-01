package composite.pattern;

import java.util.ArrayList;
import java.util.List;

public class Customer implements AccountComponent{
	
	List<AccountComponent> accounts = new ArrayList<>();
	
	public void add(AccountComponent account) {
		accounts.add(account);
	}

	@Override
	public double getBalance() {
		double total = 0;
		for(AccountComponent account : accounts) {
			total += account.getBalance();
		}
		return total;
	}
	
}