package Jdk_24;

public class Instance_of {

    public static void old_approach(Object obj){
        if(obj instanceof String){
            String s = String.valueOf(obj);
            System.out.println(s);
        }
    }

    public static void new_approach(Object obj){
        if(obj instanceof String s){
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        var s = "test";
        old_approach(s);
        new_approach(s);

        var ss = "test";
        old_approach(ss);
        new_approach(ss);
    }
}
