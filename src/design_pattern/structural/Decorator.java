package design_pattern.structural;

interface Pizza{
    String ingredient();
    double price();
}
class BasicPizza implements Pizza{
    @Override
    public String ingredient() {
        return "Basic Pizza";
    }
    @Override
    public double price() {
        return 150.00;
    }
}

abstract class PizzaDecorator implements Pizza{
    private Pizza pizza;
    public PizzaDecorator(Pizza decorratedPizza){
        this.pizza = decorratedPizza;
    }
    @Override
    public String ingredient() {
        return pizza.ingredient();
    }
    @Override
    public double price() {
        return pizza.price();
    }
}
class CheeseDecorator extends PizzaDecorator{
    public CheeseDecorator(Pizza decorratedPizza) {
        super(decorratedPizza);
    }
    @Override
    public String ingredient() {
        return super.ingredient() + " with add on Cheese";
    }
    @Override
    public double price() {
        return super.price() + 10.50;
    }
}
class TopingsDecorator extends PizzaDecorator{
    public TopingsDecorator(Pizza decorratedPizza) {
        super(decorratedPizza);
    }
    @Override
    public String ingredient() {
        return super.ingredient() + " with add on Topings";
    }
    @Override
    public double price() {
        return super.price() + 17.50;
    }
}
public class Decorator {
    public static void main(String[] args) {
        Pizza cheesePizza = new CheeseDecorator(new BasicPizza());
        System.out.println(cheesePizza.ingredient() + "  : price "+cheesePizza.price());
        Pizza cheeseTopingPizza = new TopingsDecorator(new CheeseDecorator(new BasicPizza()));
        System.out.println(cheeseTopingPizza.ingredient() + "  : Price "+cheeseTopingPizza.price());
    }
}
