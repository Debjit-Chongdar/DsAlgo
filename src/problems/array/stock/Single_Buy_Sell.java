package problems.array.stock;

// [7, 1, 5, 3, 6, 4]  => {1-6} => 5
public class Single_Buy_Sell {

    public static int maxProfitBy_single_sell(int[] stockPrices){
        int profit = 0;
        int previous_lowest_price = Integer.MAX_VALUE;
        for(int price : stockPrices){
            if(price > previous_lowest_price){
                profit = Math.max(profit, price - previous_lowest_price);
            }else{
                previous_lowest_price = price;
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfitBy_single_sell(new int[]{7, 1, 5, 3, 6, 4}));
    }
}
