package recursion;

import java.util.Scanner;

public class TicTokToe {

    public static void solveGame(char[][] board, char user){
        showBoard(board);
        // first it's available otherwise, it would have return result
        // take the input first
        System.out.println("put row value");
        int row = new Scanner(System.in).nextInt();
        System.out.println("put column value");
        int col = new Scanner(System.in).nextInt();
        // if the cell is not empty, then ask user to re input
        if(!isAvailable(board, row, col)){
            System.out.println("This cell is not available, please enter into another cell");
            solveGame(board, user);
        }
        // if available add the val and check if it solve
        board[row][col] = user;
        if(isSolved(board, row, col)){
            System.out.println("User "+user+" solved the game");
            showBoard(board);
            return;
        }
        // if there is no more cell left
        if(isAllCellFilled(board)){
            System.out.println("No one own the game");
            showBoard(board);
            return;
        }
        user = (user == 'X')? 'O' : 'X';
        solveGame(board, user);
    }
    private static boolean isSolved(char[][] board, int row, int col){
        // check each row has same value or not
        for(int r=0; r<board.length; r++){
            boolean solve = true;
            for(int c=1; c<board[r].length; c++){
                if(board[r][c] != board[r][c-1] || (board[r][c]!='X' && board[r][c] != 'O')){
                    solve = false;
                    break;
                }
            }
            if(solve){
                return solve;
            }
        }
        // check each column
        for(int c=0; c<board[0].length; c++){
            boolean solve = true;
            for(int r=1; r<board.length; r++){
                if(board[r][c] != board[r-1][c] || (board[r][c]!='X' && board[r][c] != 'O')){
                    solve=false;
                    break;
                }
            }
            if(solve){
                return solve;
            }
        }
        // check top left to bottom right
        int r=1, c=1;
        boolean solve = true;
        while(r<board.length && c<board[r].length){
            if(board[r-1][c-1] != board[r][c] || (board[r][c]!='X' && board[r][c] != 'O')){
                solve = false;
                break;
            }
            r++; c++;
        }
        if(solve){
            return solve;
        }
        // check top right to bottom left
        solve = true;
        r=1;
        c=board[0].length-2;
        while(r<board.length && c>=0){
            if(board[r-1][c+1] != board[r][c] || (board[r][c]!='X' && board[r][c] != 'O')){
                solve = false;
                break;
            }
        }
        return solve;
    }
    private static boolean isAllCellFilled(char[][] board){
        for(int r=0; r< board.length; r++){
            for(int c=0; c<board[r].length; c++){
                if(board[r][c] != 'X' && board[r][c] != 'O'){
                    return false;
                }
            }
        }
        return true;
    }
    private static boolean isAvailable(char[][] board, int row, int col){
        return (board[row][col] != 'X' && board[row][col] != 'O');
    }
    private static void showBoard(char[][] board){
        for(int r=0; r<board.length; r++){
            for(int c=0; c<board[r].length; c++){
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        solveGame(new char[3][3], 'X');
    }
}
