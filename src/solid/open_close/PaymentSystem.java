package solid.open_close;

public class PaymentSystem {

    //No update required if any new payment type introduce
    public static void pay(Payment payment, double amount){
        payment.makePayment(amount);
    }

    public static void main(String[] args) {
        Payment paypalPayment = new PaypalPayment();
        pay(paypalPayment, 20.34);
        Payment neftPayment = new NEFTPayment();
        pay(neftPayment, 2034.00);
        Payment upiPayment = new UPIPayment();
        pay(upiPayment, 50.00);
    }
}
