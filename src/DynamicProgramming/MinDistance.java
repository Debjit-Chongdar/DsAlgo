package DynamicProgramming;

// insert / replace / delete
// word1 = "monkeys", word2 = "money" , result = 2 (2 delete required)
public class MinDistance {
    public static int minOperations(String s, String t){
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m+1][n+1];
        // populate first row, if other char is empty
        for(int i=0; i<=n; i++){
            dp[0][i] = i;
        }
        // populate first column, if other char is empty
        for(int i=1; i<=m; i++){
            dp[i][0] = i;
        }
        for(int row=1; row<dp.length; row++){
            for(int col=1; col<dp[0].length; col++){
                //if both char is same then just take the previous diagonal value
                if(s.charAt(row-1) == t.charAt(col-1)){
                    dp[row][col] = dp[row-1][col-1];
                }else{
                    // if both char is not same then populate min(left, top, diagonal)+1
                    int min = Math.min(dp[row-1][col], dp[row][col-1]);
                    min = Math.min(min, dp[row-1][col-1]);
                    dp[row][col] = 1 + min;
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println("min operation required monkeys # money : "+minOperations("monkeys", "money"));
    }
}
