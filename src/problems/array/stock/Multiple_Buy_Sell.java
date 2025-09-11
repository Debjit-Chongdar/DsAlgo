package problems.array.stock;

public class Multiple_Buy_Sell {

    // [7, 1, 5, 3, 6, 4]  => 1-5(4) + 3-6(3) => 7
    public static int profit_by_multiple_transaction(int[] stockPrice){
        int profit = 0;
        for(int i=1; i<stockPrice.length; i++){
            if(stockPrice[i] > stockPrice[i-1]){
                profit += stockPrice[i] - stockPrice[i-1];
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        System.out.println(profit_by_multiple_transaction(new int[]{7, 1, 5, 3, 6, 4}));
    }
}
