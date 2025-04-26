package solid.liksov_substitution;

public class Rectangle implements Shape,ShapeHeight,ShapeLength{

    private int width;
    private int height;

    @Override
    public int area() {
        return this.height * this.width;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }
}
