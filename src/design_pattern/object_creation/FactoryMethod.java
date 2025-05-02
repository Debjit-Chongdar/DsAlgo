package design_pattern.object_creation;

interface ExtractionType {
    void getData();
}
class RaExtraction implements ExtractionType {
    @Override
    public void getData() {
        System.out.println("Ra extraction ...");
    }
}
class VarExtraction implements ExtractionType {
    @Override
    public void getData() {
        System.out.println("Var extraction ...");
    }
}

interface ExtractionFactory {
    ExtractionType getExtractionType();
}
class RaExtFactory implements ExtractionFactory {
    @Override
    public ExtractionType getExtractionType() {
        return new RaExtraction();
    }
}
class VarExtractionFactory implements ExtractionFactory {
    @Override
    public ExtractionType getExtractionType() {
        return new VarExtraction();
    }
}

class Client {
    ExtractionType extractionType;

    Client(ExtractionFactory factory) {
        extractionType = factory.getExtractionType();
    }

    ExtractionType extractionType() {
        return extractionType;
    }
}

public class FactoryMethod {
    public static void main(String[] args) {
        Client client = new Client(new RaExtFactory());
        ExtractionType raExtractionType = client.extractionType();
        raExtractionType.getData();
        Client vClient = new Client(new VarExtractionFactory());
        ExtractionType varExtractionType = vClient.extractionType();
        varExtractionType.getData();
    }
}
