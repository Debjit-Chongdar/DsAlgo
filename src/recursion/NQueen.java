package recursion;

import static recursion.Util.showPath;

public class NQueen {

    public static boolean isNQueenPossible(boolean[][] board, int row){
        //if all rows are covered then nQueen is possible
        if(row >= board.length){
            showPath(board,"Q");
            return true;
        }
        //check for all columns one by one to see if nqueen possible
        for(int c=0; c<board[row].length; c++){
            //if safe move forward for the next row
            if(isSafe(board, row, c)){
                board[row][c] = true;
                if(isNQueenPossible(board, row+1)){
                    return true;
                }
                //revert changes after check to see for the next column
                board[row][c] = false;
            }
        }
        return false;
    }
    public static int wayToRepresentNQueen(boolean[][] board, int row){
        if(row >= board.length){
            showPath(board, "Q");
            System.out.println("-----------------");
            return 1;
        }
        int count = 0;
        //check for all columns one by one to see if nqueen possible
        for(int col = 0; col<board[row].length; col++) {
            //it will check for all columns one by one
            if (isSafe(board, row, col)) {
                board[row][col] = true;
                count += wayToRepresentNQueen(board, row + 1);
                board[row][col] = false;
            }
        }
        return count;
    }
    private static boolean isSafe(boolean[][] board, int row, int col){
        //check for left columns
        for(int column=0; column < col; column++){
            if(board[row][column]){
                return false;
            }
        }
        //check for the above rows
        for(int r=0; r<row; r++){
            if(board[r][col]){
                return false;
            }
        }
        //check for top-left diagonal
        int r=row;
        int c=col;
        while(r > 0 && c > 0){
            if(board[--r][--c]){
                return false;
            }
        }
        //check for top-right diagonal
        r=row;
        c=col;
        while(r > 0 && c < board[row].length-1){
            if(board[--r][++c]){
                return false;
            }
        }
        //if none of the checks match
        return true;
    }

    public static void main(String[] args) {
        boolean[][] board = new boolean[4][4];
        System.out.println(isNQueenPossible(board, 0));
        boolean[][] board1 = new boolean[4][4];
        System.out.println("how many way we can represent NQueen for 4*4 board");
        System.out.println(wayToRepresentNQueen(board1, 0));
    }
}
