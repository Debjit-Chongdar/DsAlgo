package design_pattern.object_creation;

public class FactoryOld {
    interface Extraction{
        void getData();
        void mapResponse();
    }
    class RaExtraction implements Extraction{
        @Override
        public void getData() {
            System.out.println("Ra Extracted data");
        }
        @Override
        public void mapResponse() {
            System.out.println("RA extracted data Map to common response");
        }
    }
    class VarExtraction implements Extraction{
        @Override
        public void getData() {
            System.out.println("Var Extracted data");
        }
        @Override
        public void mapResponse() {
            System.out.println("Map Var extracted data to common response");
        }
    }
    public Extraction extraction(String type){
        switch(type.toLowerCase()){
            case "ra" : return new RaExtraction();
            case "var": return new VarExtraction();
            default: return new RaExtraction();
        }
    }

    public static void main(String[] args) {
        FactoryOld factoryOld = new FactoryOld();
        Extraction rae = factoryOld.extraction("ra");
        Extraction vex = factoryOld.extraction("VaR");
        Extraction sbm = factoryOld.extraction("SBM");
        rae.getData();
        rae.mapResponse();
        vex.getData();
        vex.mapResponse();
        sbm.getData();
        sbm.mapResponse();
    }
}
