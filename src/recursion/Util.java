package recursion;

public class Util {
    public static void showPath(boolean[][] mat, String outChar) {
        for(int r=0; r<mat.length; r++){
            for (int c=0; c<mat[r].length; c++){
                System.out.print(mat[r][c]?outChar:"-");
            }
            System.out.println();
        }
    }
}
