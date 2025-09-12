package Jdk_24;

public class Local_variable {
    // var data = 23; wrong
    // does not support instance variable and static variable
    public static void main(String[] args) {
        // support only local variabless
        var i = 2025;
        var s = "Debjit";
        var xx = 23;
        // xx = "r"; // this is also not possible
        System.out.println(i +" - "+ s);
    }
}
