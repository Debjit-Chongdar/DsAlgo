package solid.dependenycy_inversion;

public class DebitCard implements Card{

    @Override
    public void pay(double amount) {
        System.out.println(" Pay via Debit card "+amount);
    }
}
