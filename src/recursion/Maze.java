package recursion;

import static recursion.Util.showPath;

public class Maze {

    //if maze can travel only down and right direction
    public static int howManyWayToReach(int startRow, int startCol, int targetRow, int targetCol){
        int count = 0;
        if(startRow == targetRow || startCol == targetCol){
            return 1;
        }
        if(startRow < targetRow){
            count += howManyWayToReach(startRow+1, startCol, targetRow, targetCol);
        }
        if(startCol < targetCol){
            count += howManyWayToReach(startRow, startCol+1, targetRow, targetCol);
        }
        return count;
    }

    // if maze can travel all four direction
    public static int showMazePath(boolean[][] mat, int startRow, int startCol, int targetRow, int targetCol, String  path) {
        int count = 0;
        //if maze reach to the destination
        if(startRow == targetRow && startCol == targetCol){
            mat[startRow][startCol] = true;
           // showPath(mat, "*");
            path = path+" -> ["+startRow+"]["+startCol+"]";
            System.out.println(path);
           // System.out.println("-----------------");
            return 1;
        }
        //if current position is blocked/already visited
        if(mat[startRow][startCol]){
            return 0;
        }
        // if current position is not blocked
        mat[startRow][startCol] = true;
        path = path+" -> ["+startRow+"]["+startCol+"]";
        //if it can travel down
        if(startRow < mat.length-1){
            count += showMazePath(mat, startRow+1, startCol, targetRow, targetCol, path);
        }
        //if it can travel right
        if(startCol < mat[startRow].length-1){
            count += showMazePath(mat, startRow, startCol+1, targetRow, targetCol, path);
        }
        //if it can travel up
        if(startRow > 0){
            count += showMazePath(mat, startRow-1, startCol, targetRow, targetCol, path);
        }
        //if it can travel left
        if(startCol > 0){
            count += showMazePath(mat, startRow, startCol-1, targetRow, targetCol,path);
        }
        //revert the changes once reach block/reached destination
        mat[startRow][startCol] = false;
        return count;
    }

    public static void main(String[] args) {
        System.out.println(howManyWayToReach(0,1,3,2));
        System.out.println("without blocker how to reach 0/1 to 3/2");
        boolean[][] mat = new boolean[4][4];
        System.out.println(showMazePath(mat, 0,1,3,2,""));
        //test if maze ca reach to target
        System.out.println("with blocker how to reach 0/1 to 3/2");
        boolean[][] matWithBlocker = new boolean[4][4];
        matWithBlocker[1][1]=true;
        matWithBlocker[2][2]=true;
        matWithBlocker[3][3]=true;
        matWithBlocker[3][0]=true;
        System.out.println(showMazePath(matWithBlocker,0,1, 3,2, ""));
    }
}
