package decorator.design;
class TaxDecorator extends PaymentDecorator {

    public TaxDecorator(PaymentProcessor processor) {
        super(processor);
    }

    public void process(double amount) {

        double tax = amount * 0.18;

        System.out.println("Applying tax: " + tax);

        super.process(amount + tax);
    }
}