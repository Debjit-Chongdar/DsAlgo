package design_pattern.object_creation;

interface Adj {
    void adjustment();
}
class RaAdj implements Adj {
    @Override
    public void adjustment() {
        System.out.println("Ra adjustment ...");
    }
}
class VarAdj implements Adj {
    @Override
    public void adjustment() {
        System.out.println("Var adjustment ...");
    }
}

interface AdjFactory {
    Adj getAdj();
}
class RaAdjFactory implements AdjFactory {
    @Override
    public Adj getAdj() {
        return new RaAdj();
    }
}
class VarAdjFactory implements AdjFactory {
    @Override
    public Adj getAdj() {
        return new VarAdj();
    }
}

public class AbstructFactory {
    // difference with Factory method is here we don't have client
    public static void main(String[] args) {
        AdjFactory factory = new RaAdjFactory();
        Adj adj = factory.getAdj();
        adj.adjustment();
    }
}
