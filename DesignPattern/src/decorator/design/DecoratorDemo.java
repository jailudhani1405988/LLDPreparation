package decorator.design;
public class DecoratorDemo {

    public static void main(String[] args) {

        PaymentProcessor processor =
                new AuditLogDecorator(
                    new TaxDecorator(
                        new FraudCheckDecorator(
                            new BasicPaymentProcessor()
                        )
                    )
                );

        processor.process(1000);
    }
}