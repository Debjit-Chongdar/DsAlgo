package design_pattern.object_creation;

public class FactoryMethod {
    interface Ext{
        void getData();
    }
    static class RaExt implements Ext{
        @Override
        public void getData() {
            System.out.println("Ra extraction ...");
        }
    }
    static class VarExt implements Ext{
        @Override
        public void getData() {
            System.out.println("Var extraction ...");
        }
    }
    interface ExtFactory{
        Ext getExt();
    }
    static class RaExtFactory implements ExtFactory{
        @Override
        public Ext getExt() {
            return new RaExt();
        }
    }
    static class VarExtFactory implements ExtFactory{
        @Override
        public Ext getExt() {
            return new VarExt();
        }
    }
    static class Client{
        Ext ext;
        Client(ExtFactory factory){
            ext = factory.getExt();
        }
        Ext extract(){
            return ext;
        }
    }

    public static void main(String[] args) {
        Client client = new Client(new RaExtFactory());
        Ext raExt= client.extract();
        raExt.getData();
        Client vClient = new Client(new VarExtFactory());
        Ext varExt= vClient.extract();
        varExt.getData();
    }
}
