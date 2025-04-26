package solid.open_close;

public class PaypalPayment implements Payment{
    @Override
    public void makePayment(double amount) {
        System.out.println("PayPal payment : "+amount);
    }
}
