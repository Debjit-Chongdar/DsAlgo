package solid.liksov_substitution;

public class Square implements Shape,ShapeLength{

    private int length;

    @Override
    public int area() {
        return (int)Math.pow(this.length, 2);
    }

    @Override
    public void setWidth(int width) {
        this.length = width;
    }
}
