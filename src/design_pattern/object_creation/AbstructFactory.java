package design_pattern.object_creation;

public class AbstructFactory {
    interface Adj{
        void adjustment();
    }
    static class RaAdj implements Adj{
        @Override
        public void adjustment() {
            System.out.println("Ra adjustment ...");
        }
    }
    static class VarAdj implements Adj{
        @Override
        public void adjustment() {
            System.out.println("Var adjustment ...");
        }
    }
    interface AdjFactory {
        Adj getAdj();
    }
    static class RaAdjFactory implements AdjFactory{
        @Override
        public Adj getAdj() {
            return new RaAdj();
        }
    }
    static class VarAdjFactory implements AdjFactory{
        @Override
        public Adj getAdj() {
            return new VarAdj();
        }
    }
// difference with Factory method is here we don't have client
    public static void main(String[] args) {
        AdjFactory factory = new RaAdjFactory();
        Adj adj =  factory.getAdj();
        adj.adjustment();
    }
}
