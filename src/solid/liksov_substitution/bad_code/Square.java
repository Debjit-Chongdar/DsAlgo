package solid.liksov_substitution.bad_code;

public class Square implements Shape{

    private int length;

    @Override
    public void setHeight(int height) {
        this.length=height;
    }

    @Override
    public void setWidth(int width) { //unnecessary implementation
        this.length=width;
    }

    @Override
    public int area() {
        return length*length;
    }
}
