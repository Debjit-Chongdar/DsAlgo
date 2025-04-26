package solid.open_close;

public class NEFTPayment implements Payment{
    @Override
    public void makePayment(double amount) {
        System.out.println("NEFT payment : "+amount);
    }
}
