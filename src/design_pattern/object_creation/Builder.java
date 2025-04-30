package design_pattern.object_creation;

public class Builder {
    static class Emp{
        private int id;
        private String name;
        static class EmpBuilder{
            private static int id;
            private static String name;
            private static EmpBuilder empBuilder;
            //this constructor must create and assign Emp object
            public EmpBuilder(){
                empBuilder = new EmpBuilder();
            }
            //must be static method to call class.method() and return employee Builder
            static EmpBuilder id(int id){
                EmpBuilder.id = id;
                return empBuilder;
            }
            //must be static method to call class.method() and return employee Builder
            static EmpBuilder name(String name){
                EmpBuilder.name = name;
                return empBuilder;
            }
            //create actual Emp object in this method
            static Emp build(){
                Emp emp = new Emp();
                emp.id = id;
                emp.name = name;
                return emp;
            }
        }
    }

    public static void main(String[] args) {
        Emp emp = Emp.EmpBuilder.id(2).name("ABC").build();
        System.out.println(emp.id + "  "+emp.name);
    }
}
