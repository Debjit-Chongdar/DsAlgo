package DynamicProgramming.recursion;

public class Factorial {
    // 6 => 6*5*4*3*2*1 = 720
    public static int fact(int n){
        if(n < 3){
            return n;
        }
        return n * fact(n-1);
    }

    public static void main(String[] args) {
        System.out.println("6 factorial = "+ fact(6));
    }
}
