package decorator.design;
abstract class PaymentDecorator implements PaymentProcessor {

    protected PaymentProcessor processor;

    public PaymentDecorator(PaymentProcessor processor) {
        this.processor = processor;
    }

    public void process(double amount) {
        processor.process(amount);
    }
}