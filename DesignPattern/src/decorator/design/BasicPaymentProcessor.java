package decorator.design;
class BasicPaymentProcessor implements PaymentProcessor {

    public void process(double amount) {
        System.out.println("Processing payment: " + amount);
    }
}