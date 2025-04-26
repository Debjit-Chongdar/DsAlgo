package solid.liksov_substitution.bad_code;

public class Rectangle implements Shape{
    private int height;
    private int width;

    @Override
    public void setHeight(int height){
        this.height = height;
    }
    @Override
    public void setWidth(int width){
        this.width = width;
    }
    @Override
    public int area(){
        return this.height * this.width;
    }
}
