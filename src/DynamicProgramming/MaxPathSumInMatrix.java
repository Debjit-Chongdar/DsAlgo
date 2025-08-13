package DynamicProgramming;
//only can travers down and down diagonally
//  [3,6,1]     [3,6,1]
//  [2,3,4]     [8,9,10]
//  [5,5,1]     [14,15,11]
public class MaxPathSumInMatrix {

    public static int maxPathSum(int[][] metrix){
        for(int row=1; row<metrix.length; row++){
            for(int col=0; col<metrix[0].length; col++){
                // consider above cell value
                int maxVal = metrix[row][col] + metrix[row-1][col];
                // consider above left cell value
                if(col > 0){
                    maxVal = Math.max(maxVal, metrix[row][col] + metrix[row-1][col-1]);
                }
                // consider above right cell value
                if(col < metrix[0].length-1){
                    maxVal = Math.max(maxVal, metrix[row][col] + metrix[row-1][col+1]);
                }
                metrix[row][col] = maxVal;
            }
        }
        // find max value from last row
        int max = Integer.MIN_VALUE;
        for(int c=0; c<metrix[0].length; c++){
            max = Math.max(max, metrix[metrix.length-1][c]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] matrix = {{3,6,1},
                {2,3,4},
                {5,5,1}};
        System.out.println(" Max val : "+maxPathSum(matrix));
    }
}

