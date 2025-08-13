package DynamicProgramming;

public class PathSumInMatrix {
    public static int pathSum(int[][] matrix){
        // populate 1 in first row all column
        for(int c=0; c<matrix[0].length; c++){
            matrix[0][c] = 1;
        }
        // populate 1 in first column all rows
        for(int r=0; r<matrix.length; r++){
            matrix[r][0] = 1;
        }
        //add left and top cell value
        for(int r=1; r<matrix.length; r++){
            for(int c=1; c<matrix[0].length; c++){
                matrix[r][c] = matrix[r-1][c] + matrix[r][c-1];
            }
        }
        return matrix[matrix.length-1][matrix[0].length-1];
    }

    public static void main(String[] args) {
        System.out.println("3*3 matrics path = "+pathSum(new int[3][3]));
        System.out.println("3*4 matrics path = "+pathSum(new int[3][4]));
    }
}
