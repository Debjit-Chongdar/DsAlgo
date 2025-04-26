package solid.open_close;

public class UPIPayment implements Payment{

    @Override
    public void makePayment(double amount) {
        System.out.println("PayPal payment : "+amount);
    }

}
