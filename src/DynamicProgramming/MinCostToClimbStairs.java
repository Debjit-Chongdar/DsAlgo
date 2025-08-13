package DynamicProgramming;

public class MinCostToClimbStairs {
// we can jump one step only
    public static int minCostToClimbTop(int[] costs){
        int[] dp = new int[costs.length];
        dp[0] = costs[0];
        dp[1] = costs[1];
        for(int i=2; i<costs.length; i++){
            // if we came from previous step
            int pre = dp[i-1] + costs[i];
            //if  we came by jump the previous step
            int preJump = dp[i-2] + costs[i];
            dp[i] = Math.min(pre, preJump);
        }
        // here also we can jump last stair
        return Math.min(dp[costs.length-2], dp[costs.length-1]);
    }

    public static void main(String[] args) {
        System.out.println("Minimum cost required to reach top : "+minCostToClimbTop(new int[]{10, 20, 5, 2}));
    }
}
