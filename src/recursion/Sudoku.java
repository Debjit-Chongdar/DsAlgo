package recursion;

public class Sudoku {

    public static boolean isSudoku(int[][] arr){
        int row = -1;
        int col = -1;
        boolean isSolved = true;
        //to find which cell's value is 0
        for(int r=0; r< arr.length; r++){
            for(int c=0; c<arr[r].length; c++){
                //if we found any cell value is 0 then stop there and try to fill the value
                if(arr[r][c] == 0){
                    row = r;
                    col = c;
                    isSolved = false;
                    break;
                }
            }
            //to break the outer loop
            if(!isSolved){
                break;
            }
        }
        //if it's already solved then we can return true no need to look forward
        if(isSolved){
            return true;
        }
        //check with 1 to length each value to see if solve is possible
        for(int i=1; i<=arr.length; i++){
            //check if i value is safe for the cell or not, if not then check with next value
            if(isSafe(arr, row, col, i)){
                arr[row][col] = i;
                if(isSudoku(arr)){
                    //show(arr);
                    return true;
                }
                arr[row][col] = 0;
            }
        }
        return isSolved;
    }
    private static boolean isSafe(int[][] arr, int row, int col, int value){
        //if this val already present in entire row or entire column
        for(int i=0; i<arr.length; i++){
            if(arr[i][col] == value || arr[row][i]==value){
                return false;
            }
        }
        //check within the cube if same value already present
        //if length = 9 then sqrt = 3
        int sqrt = (int)Math.sqrt(arr.length);
        int startRow = row - (row % sqrt);
        int startCol = col - (col % sqrt);
        for(int r=startRow; r<startRow+sqrt; r++){
            for(int c=startCol; c<startCol+sqrt; c++){
                if(arr[r][c] == value){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] arr = {
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}
        };
        System.out.println(isSudoku(arr));
        show(arr);
        int[][] arr1 = {
                { 8, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 3, 6, 0, 0, 0, 0, 0 },
                { 0, 7, 0, 0, 9, 0, 2, 0, 0 },
                { 0, 5, 0, 0, 0, 7, 0, 0, 0 },
                { 0, 0, 0, 0, 4, 5, 7, 0, 0 },
                { 0, 0, 0, 1, 0, 0, 0, 3, 0 },
                { 0, 0, 1, 0, 0, 0, 0, 6, 8 },
                { 0, 0, 8, 5, 0, 0, 0, 1, 0 },
                { 0, 9, 0, 0, 0, 0, 4, 0, 0 }
        };
        System.out.println(isSudoku(arr1));
        show(arr1);
    }
    private static void show(int[][] arr){
        for (int r=0; r<arr.length; r++){
            for(int c=0; c<arr[r].length;c++){
                System.out.print(arr[r][c]+" ");
            }
            System.out.println();
        }
        System.out.println("--------------------");
    }
}
