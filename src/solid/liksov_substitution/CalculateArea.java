package solid.liksov_substitution;

public class CalculateArea {

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(5);
        rectangle.setWidth(7);
        int area = rectangle.area();
        System.out.println("Area of ractangle : "+area);

        Square square = new Square();
        square.setWidth(6);
        int sArea = square.area();
        System.out.println("Area of square :"+sArea);
    }
}
