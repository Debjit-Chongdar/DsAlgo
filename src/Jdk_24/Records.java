package Jdk_24;

record Employee(String name, int age, double sal){}

public class Records {
    public static void main(String[] args) {
        Employee e = new Employee("Name", 34, 67800.98);
        System.out.println(e.name() +" "+ e.age() +" "+ e.sal());
    }
}
