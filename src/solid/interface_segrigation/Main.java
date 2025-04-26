package solid.interface_segrigation;

public class Main {
    public static void main(String[] args) {
        BasicPrinter printer = new BasicPrinter();
        printer.read();
        printer.write();
        //printer.scan(); method is not present
        AdvancePrinter advancePrinter = new AdvancePrinter();
        advancePrinter.read();
        advancePrinter.scan();
        advancePrinter.write();
    }
}
