package solid.interface_segrigation;

public class BasicPrinter implements Reader,Writer{
    @Override
    public void read() {
        System.out.println("Read file");
    }

    @Override
    public void write() {
        System.out.println("Write file");
    }
}
