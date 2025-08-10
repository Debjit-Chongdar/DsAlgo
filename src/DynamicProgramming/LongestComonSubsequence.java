package DynamicProgramming;

public class LongestComonSubsequence {

    public static int LCS(String s, String t){
        int r = s.length()+1;
        int c = t.length()+1;
        int[][] dp = new int[r][c];
        // populate first row all column and first column all rows value with 0
        // but by default it's 0 in all the cell so no need to populate
        for(int row = 1; row<r; row++){
            for(int col = 1; col<c; col++){
                // if equals then 1+ previous diagonal val
                if(s.charAt(row-1) == t.charAt(col-1)){
                    dp[row][col] = dp[row-1][col-1] +1;
                }else{ // max val between left and top
                    dp[row][col] = Math.max(dp[row][col-1], dp[row-1][col]);
                }
            }
        }
        return dp[r-1][c-1];
    }

    public static void main(String[] args) {
        System.out.println("LCS of cat # crabt : "+ LCS("cat", "crabt"));
        System.out.println("LCS of abcd # abcd : "+ LCS("abcd", "abcd"));
        System.out.println("LCS of abcd # efgh : "+ LCS("abcd", "efgh"));
        System.out.println("LCS of abbcbd # xaabccdd : "+ LCS("abbcbd", "xaabccdd"));
    }
}
