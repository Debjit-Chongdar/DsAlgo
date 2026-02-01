package problems.array;

import java.util.Arrays;

public class CarFleet {
    static class Pair{
        int position;
        int speed;
        double timeToReachDestination;
        public Pair(int position, int speed, int destination){
            this.position = position;
            this.speed = speed;
            this.timeToReachDestination = (destination - position) / speed;
        }
    }
    public static int carFleet(int target, int[] position, int[] speed) {
        Pair[] pairs = new Pair[position.length];
        for(int i=0; i<position.length; i++){
            pairs[i] = new Pair(position[i], speed[i], target);
        }
        Arrays.sort(pairs, (a,b)-> a.position-b.position);
        double lastTime = pairs[pairs.length-1].timeToReachDestination;
        int count = 1;
        for(int i=pairs.length-2; i>=0; i--){
            if(pairs[i].timeToReachDestination > lastTime){
                count++;
            }
            lastTime = Math.max(lastTime, pairs[i].timeToReachDestination);
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

        int[] position2 = {5,4,7};
        int[] speed2 = {2,2,1};
        System.out.println(carFleet(10, position2, speed2));
    }
}
