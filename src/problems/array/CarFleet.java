package problems.array;

import java.util.Arrays;

public class CarFleet {
    static class Pair{
        int position;
        int speed;
        public Pair(int position, int speed){
            this.position = position;
            this.speed = speed;
        }
    }
    public static int carFleet(int target, int[] position, int[] speed) {
        Pair[] pairs = new Pair[position.length];
        for(int i=0; i<position.length; i++){
            pairs[i] = new Pair(position[i], speed[i]);
        }
        Arrays.sort(pairs, (a,b)-> a.position-b.position);
        double[] timeToReachDestination = new double[position.length];
        for(int i=0; i<pairs.length; i++){
            int distance = target-pairs[i].position;
            timeToReachDestination[i] = distance/pairs[i].speed;
        }
        int count = 1;
        for(int i=pairs.length-2; i>=0; i--){
            if(timeToReachDestination[i] > timeToReachDestination[i+1]){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] position = {1,4};
        int[] speed = {3,2};
        System.out.println(carFleet(10, position, speed));

        int[] position1 = {4,1,0,7};
        int[] speed1 = {2,2,1,1};
        System.out.println(carFleet(10, position1, speed1));
    }
}
