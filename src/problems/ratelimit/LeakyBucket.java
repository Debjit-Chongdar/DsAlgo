package problems.ratelimit;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// initially bucket should be empty
// during request it will check time difference from last updated time
// convert this difference in second and multiply with frequency
// update the bucket content/water
//if bucket has empty space then it will allow request otherwise overflow/reject
public class LeakyBucket {
    private int bucketCapacity;
    private double leak_rate_per_second;

    private Map<String, Double> userWater;
    private Map<String, Long> userLastLeakTime;

    public LeakyBucket(int maxRequest, double frequency){
        this.bucketCapacity = maxRequest;
        this.leak_rate_per_second = frequency;
        this.userWater = new HashMap<>();
        this.userLastLeakTime = new HashMap<>();
    }

    public synchronized boolean isAllowRequest(String user){
        leak(user);

        //check is bucket has space to pour more water or overflow
        if(userWater.get(user) < bucketCapacity){
            userWater.replace(user, userWater.get(user) +1 );
            return true;
        }
        return false;
    }

    private void leak(String user) {
        long now = new Date().getTime();

        userWater.putIfAbsent(user, 0.0);
        userLastLeakTime.putIfAbsent(user, now);
        // divide by 1000 to convert millisecond to second
        long elapsedSecond = (now - userLastLeakTime.get(user)) / 1000;
        double leakedWater = elapsedSecond * leak_rate_per_second;
        //always check with 1 , if we can decrease by 1 or not
        if(leakedWater >= 1.0){ // don't use > 0, it could be 0.00001
            //if request come after long time then tokenToLeak can be bigger than existing
            //bucket content can not be negative
            double tokenLeft = Math.max(0.0, userWater.get(user) - leakedWater);
            userWater.replace(user, tokenLeft);
            userLastLeakTime.replace(user, now);
        }
    }

    public static void main(String[] args) {
        LeakyBucket leakyBucket = new LeakyBucket(5, 1);
        System.out.println("Sending 7 requests immediately:");
        for(int i=0; i<7; i++){
            System.out.println(leakyBucket.isAllowRequest("Abc"));
        }
        System.out.println("\nWaiting 3 seconds...");
        try{
            Thread.sleep(3000);
        }catch (InterruptedException ie){
            ie.getStackTrace();
        }
        for(int i=1; i<4; i++){
            System.out.println(leakyBucket.isAllowRequest("Abc"));
        }
    }
}
