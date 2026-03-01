package composite.pattern;
/*
 * 
 * Think of a simple banking system like:

Customer
   ├── Savings Account
   ├── Current Account
   └── Fixed Deposit

Now imagine:

Each account has a getBalance() method

A Customer wants to see Total Net Worth

Instead of writing special logic in Customer, we use Composite Pattern.

 * 
 * 
 * You might end up writing:

if (account instanceof SavingsAccount) { ... }
if (account instanceof FixedDeposit) { ... }

Or:

double total = savings.getBalance()
              + current.getBalance()
              + fd.getBalance();

This breaks extensibility when:

New account types are added (Loan Account, Demat Account, etc.)
 * 
 * */


interface AccountComponent{
	 double getBalance();
}