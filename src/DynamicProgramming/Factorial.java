package DynamicProgramming;

public class Factorial {
    public static int fact(int n){
        int[] dp = new int[n+1];
        dp[1] = 1;
        for(int i=2; i<dp.length; i++){
            dp[i] = i * dp[i-1];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println("6 factorial = "+ fact(6));
    }
}
