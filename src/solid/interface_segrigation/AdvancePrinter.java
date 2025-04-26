package solid.interface_segrigation;

public class AdvancePrinter implements Reader,Writer,Scanner{
    @Override
    public void read() {
        System.out.println("Read file");
    }

    @Override
    public void scan() {
        System.out.println("Scan file");
    }

    @Override
    public void write() {
        System.out.println("Write file");
    }
}
