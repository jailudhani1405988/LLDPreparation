package decorator.design;
class AuditLogDecorator extends PaymentDecorator {

    public AuditLogDecorator(PaymentProcessor processor) {
        super(processor);
    }

    public void process(double amount) {

        System.out.println("Logging transaction for audit");

        super.process(amount);
    }
}