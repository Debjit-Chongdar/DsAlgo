package problems.array.stock;

// [7, 1, 5, 3, 6, 4]  => {1-5} 4 + {3-6} 3 => 7
public class Two_Buy_Sell {

    public static int maxProfit(int[] prices) {
            int n = prices.length;
            if (n == 0) return 0;

            int[] left = new int[n];   // Max profit up to day i
            int[] right = new int[n];  // Max profit from day i to end

            // LEFT PASS: Max profit from [0...i]
            int minPrice = prices[0];
            for (int i = 1; i < n; i++) {
                minPrice = Math.min(minPrice, prices[i]);
                left[i] = Math.max(left[i - 1], prices[i] - minPrice);
            }

            // RIGHT PASS: Max profit from [i...n-1]
            int maxPrice = prices[n - 1];
            for (int i = n - 2; i >= 0; i--) {
                maxPrice = Math.max(maxPrice, prices[i]);
                right[i] = Math.max(right[i + 1], maxPrice - prices[i]);
            }

            // Combine the two parts
            int maxProfit = 0;
            for (int i = 0; i < n; i++) {
                maxProfit = Math.max(maxProfit, left[i] + right[i]);
            }

            return maxProfit;
        }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }
}
