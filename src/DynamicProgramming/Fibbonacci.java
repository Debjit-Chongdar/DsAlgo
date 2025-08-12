package DynamicProgramming;

public class Fibbonacci {

    public static int fibbonacci(int number){
        int dp[] = new int[number+1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i=2; i<=number; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[number];
    }

    public static void main(String[] args) {
        System.out.println("fibonacci of 15 = "+fibbonacci(15));
    }
}
