package solid.dependenycy_inversion.bad_code;

public class SwipeMachine {

    private DebitCard debitCard;

    public SwipeMachine(DebitCard card){
        this.debitCard = card;
    }

    public void getPayment(double amount){
        debitCard.pay(amount);
    }

    public static void main(String[] args) {
        SwipeMachine swipeMachine = new SwipeMachine(new DebitCard());
        swipeMachine.getPayment(5470 );
        //if we have to introduce credit card payment we have to make lots of changes
    }
}
