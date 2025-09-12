package Jdk_24;

sealed interface Shape permits Circle, Rectangle{
    public double getArea();
}

record Circle(int radious) implements Shape{
    public double getArea(){
        return Math.PI * radious * radious;
    }
}
record Rectangle(int length, int width) implements Shape{
    public double getArea(){
        return length() * width();
    }
}
//record Square(int length) implements Shape{} // it's not possible

public class Sealed_Class {
    public static void main(String[] args) {
        Shape cir = new Circle(5);
        System.out.println(cir.getArea());
        Shape rec = new Rectangle(5, 4);
        System.out.println(rec.getArea());
    }
}
