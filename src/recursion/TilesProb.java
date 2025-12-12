package recursion;

public class TilesProb {
    //total area n*2, tiles area 2*1
    public static int numberOfWayArrangeTiles(int n){
        if(n<3){
            return n;
        }
        return numberOfWayArrangeTiles(n-2) + numberOfWayArrangeTiles(n-1);
    }
    // total area=n*1 , tiles area = m*1
    public static int numOfWayArrangeTiles(int n, int m){
        if(m>n){
            return 1;
        }
        if(m == n){
            return 2;
        }
        return numOfWayArrangeTiles(n-m, m) + numOfWayArrangeTiles(n-1, m);
    }

    public static void main(String[] args) {
        System.out.println("number of way if area = 4*2 : "+numberOfWayArrangeTiles(4));
        System.out.println("number of way if area = 4*2 : "+numOfWayArrangeTiles(4, 2));
    }
}
