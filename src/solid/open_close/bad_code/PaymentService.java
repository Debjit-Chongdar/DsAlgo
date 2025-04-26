package solid.open_close.bad_code;

public class PaymentService {

    private void payPalPayment(double amount){
        System.out.println("Payment via PayPal : "+amount);
    }
    private void neftTransfer(double amount){
        System.out.println("Payment via NEFT : "+amount);
    }
    //Add UPI
    private void upiPayment(double amount){
        System.out.println("Pay via UPI : "+amount);
    }
    public void pay(double amount, String paymentType){
        switch (paymentType.toUpperCase()){
            case "PAYPAL" : payPalPayment(amount);
            break;
            case "NEFT" : neftTransfer(amount);
            break;
            //Add UPI
            case "UPI" : upiPayment(amount);
            break;
            default:
                System.out.println("No such payment type acceptable");
                break;
        }
    }

    public static void main(String[] args) {
        PaymentService ps = new PaymentService();
        ps.pay(20.34, "paypal");
        ps.pay(2034.00, "neft");
        ps.pay(50.00,"upi");
    }

}
