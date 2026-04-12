package decorator.design;
class FraudCheckDecorator extends PaymentDecorator {

    public FraudCheckDecorator(PaymentProcessor processor) {
        super(processor);
    }

    public void process(double amount) {

        System.out.println("Performing Fraud Check");

        super.process(amount);
    }
}