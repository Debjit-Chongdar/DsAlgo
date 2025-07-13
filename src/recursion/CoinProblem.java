package recursion;

public class CoinProblem {

    public static int minCoinRequired(int amount, int[] coins){
        if(amount == 0){
            return 0;
        }
        int minCoins = Integer.MAX_VALUE;
        for(int coin : coins){
            if(amount >= coin) {
                minCoins = Math.min(minCoins, 1 + minCoinRequired(amount-coin, coins));
            }
        }
        return minCoins;
    }

    public static int wayToFormAmount(int amount, int[] coins){
        if(amount == 0){
            return 1;
        }
        int ways = 0;
        for(int coin : coins){
            if(amount >= coin){
                ways += wayToFormAmount(amount-coin, coins);
            }
        }
        return ways;
    }

    public static void main(String[] args) {
        System.out.println(minCoinRequired(8, new int[]{1,2,5}));
        System.out.println(wayToFormAmount(8, new int[]{1,2,5}));
    }
}
