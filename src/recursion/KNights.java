package recursion;

import static recursion.Util.showPath;

public class KNights {

    public static int kNights(boolean[][] board, int row, int col, int numOfKnight){
        int way = 0;
        //this is the fulfilment of condition
        if(numOfKnight==0){
            showPath(board, "K");
            System.out.println("-------------------------");
            return 1;
        }
        //in case if row reached to the end
        if(row >= board.length){
            return 0;
        }
        //if column reached to the end of board
        if(col >= board[0].length){
            return kNights(board, row+1, 0, numOfKnight);
        }
        //if this row-col position is safe then mark the place and move forward to fulfil the condition
        if(isSafe(board, row, col)){
            board[row][col] = true;
            way += kNights(board, row, col+1, numOfKnight-1);
            board[row][col] = false;
        }
        //even if the place is not safe move forward to fulfil the condition
        way += kNights(board, row, col+1, numOfKnight);
        return way;
    }

    private static boolean isSafe(boolean[][] board, int row, int col){
        //to check if row-1/col-2  and row-1/col+2 position is available then it's safe or not
        if(row > 0){
            if(col > 1 && board[row-1][col-2]){
                return false;
            }
            if(col < board[0].length-2 && board[row-1][col+2]){
                return false;
            }
        }
        //to check if row-2/col-1  and row-2/col+1 position is available then it's safe or not
        if(row > 1){
            if(col > 0 && board[row-2][col-1]){
                return false;
            }
            if(col < board[row].length-1 && board[row-2][col+1]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        boolean[][] board = new boolean[4][4];
        System.out.println(kNights(board, 0, 0, board.length));
    }
}
