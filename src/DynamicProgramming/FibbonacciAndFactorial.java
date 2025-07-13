package DynamicProgramming;

public class FibbonacciAndFactorial {

    public static int fib(int number){
        if(number < 2){
            return number;
        }
        return fib(number-1) + fib(number-2);
    }

    public static int fibbonacci(int number){
        int dp[] = new int[number+1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i=2; i<=number; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[number];
    }

    public static int fact(int number){
        if(number < 3){
            return number;
        }
        return number * fact(number-1);
    }

    public static int factorial(int number){
        int[] dp = new int[number+1];
        dp[1] = 1;
        for(int i=2; i<=number; i++){
            dp[i] = i * dp[i-1];
        }
        return dp[number];
    }
    public static void main(String[] args) {
        System.out.println("fibonacci of 15 = "+fib(15));
        System.out.println("fibonacci of 15 = "+fibbonacci(15));
        System.out.println("factorial of 6 = "+fact(6));
        System.out.println("factorial of 6 = "+factorial(6));
    }
}
