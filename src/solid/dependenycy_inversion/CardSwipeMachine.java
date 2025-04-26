package solid.dependenycy_inversion;

public class CardSwipeMachine {

    private Card card;

    public CardSwipeMachine(Card card){
        this.card = card;
    }

    public void getPayment(double amount){
        card.pay(amount);
    }

    public static void main(String[] args) {
        Card debitCard = new DebitCard();
        CardSwipeMachine swipeMachine = new CardSwipeMachine(debitCard);
        swipeMachine.getPayment(500);
        Card creditCard = new CreditCard();
        CardSwipeMachine cardSwipeMachine = new CardSwipeMachine(creditCard);
        cardSwipeMachine.getPayment(400);
    }
}
