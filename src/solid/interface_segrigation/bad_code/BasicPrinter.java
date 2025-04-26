package solid.interface_segrigation.bad_code;

public class BasicPrinter implements Printer{

    @Override
    public void scan() {
        //Scan is not possible by basic Printer
        //even though we don't have this functionality, we are forced to implement that
    }

    @Override
    public void read() {
        System.out.println("Read the file");
    }

    @Override
    public void write() {
        System.out.println("Write in file");
    }
}
