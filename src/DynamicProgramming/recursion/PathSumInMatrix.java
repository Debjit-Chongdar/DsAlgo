package DynamicProgramming.recursion;

// only right and down move is possible
public class PathSumInMatrix {
    public static int maximumPathStartToEnd(int[][] matrix){
        return maxPath(matrix, 0, 0);
    }
    private static int maxPath(int[][] matrix, int row, int col){
        if(row >= matrix.length-1 || col >= matrix[0].length-1){
            return 1;
        }
        int total =0;
        // if right move
        total += maxPath(matrix, row, col+1);
        // if down move
        total += maxPath(matrix, row+1, col);
        return total;
    }

    public static void main(String[] args) {
        System.out.println("3*3 matrics path = "+maximumPathStartToEnd(new int[3][3]));
        System.out.println("3*4 matrics path = "+maximumPathStartToEnd(new int[3][4]));
    }
}
