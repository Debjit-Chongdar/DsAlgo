package DynamicProgramming.recursion;

//only can travers down and down diagonally
public class MaxPathSumInMatrix {
    public static int maxPathSum(int[][] matrix){
        // max val if it start from first row first cell
        int maxVal = maxSum(matrix, 0,0);
        // check for first row other cells
        for(int i=1; i<matrix.length; i++){
            maxVal = Math.max(maxVal, maxSum(matrix, 0, i));
        }
        return maxVal;
    }
    private static int maxSum(int[][] matrix, int row, int col){
        if(row == matrix.length-1){
            return matrix[row][col];
        }
        // travers down
        int maxVal = matrix[row][col] + maxSum(matrix, row+1, col);
        // travers left diagonal
        if(col > 0){
            maxVal = Math.max(maxVal, matrix[row][col] + maxSum(matrix, row+1, col-1));
        }
        // travers right diagonal down
        if(col < matrix[0].length-1){
            maxVal = Math.max(maxVal, matrix[row][col] + maxSum(matrix, row+1, col+1));
        }
        return maxVal;
    }

    public static void main(String[] args) {
        int[][] matrix = {{3,6,1},
                          {2,3,4},
                          {5,5,1}};
        System.out.println(" Max val : "+maxPathSum(matrix));
    }
}
