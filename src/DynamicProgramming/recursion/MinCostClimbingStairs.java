package DynamicProgramming.recursion;

public class MinCostClimbingStairs {
    public static int minCostClimbingStairs(int[] costs){
        return minCost(costs, 0);
    }
    private static int minCost(int[] costs, int idx){
        if(costs.length < 2){
            return 0;
        }
        if(idx >= costs.length){
            return 0;
        }
        // consider next step
        int in = costs[idx] + minCost(costs, idx+1);
        // skip next step
        int out = costs[idx] + minCost(costs, idx+2);
        return Math.min(in, out);
    }

    public static void main(String[] args) {
        System.out.println("Minimum cost required to reach top : "+minCostClimbingStairs(new int[]{10, 20, 5, 2}));
        System.out.println("Minimum cost required to reach top : "+minCostClimbingStairs(new int[]{10, 20}));
        System.out.println("Minimum cost required to reach top : "+minCostClimbingStairs(new int[]{10}));
    }
}
