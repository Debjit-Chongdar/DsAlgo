package DynamicProgramming.recursion;

public class Fibbonacci {

    // 6 => 0,1,1,2,3,5,8
    public static int fib(int n){
        if(n<2){
            return n;
        }
        return fib(n-1) + fib(n-2);
    }

    public static void main(String[] args) {
        System.out.println(fib(6));
    }
}
