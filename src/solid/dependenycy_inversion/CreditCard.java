package solid.dependenycy_inversion;

public class CreditCard implements Card{

    @Override
    public void pay(double amount) {
        System.out.println("Pay via credit card "+amount);
    }
}
