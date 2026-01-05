package problems.ratelimit;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

// Simple and Easy
public class RateLimiter_fixedWindow {
    private int maxRequest;
    private long interval;

    private ConcurrentMap<String, Integer> userRequestCount = new ConcurrentHashMap<>();
    private ConcurrentMap<String, Long> userStartTime = new ConcurrentHashMap<>();

    public RateLimiter_fixedWindow(int maxRequest, long interval){
        this.interval = interval;
        this.maxRequest = maxRequest;
    }

    public synchronized boolean allowRequest(String user){
        long now = new Date().getTime();

        userStartTime.putIfAbsent(user, now);
        userRequestCount.putIfAbsent(user, 0);

        if(now - userStartTime.get(user) > interval){
            userStartTime.replace(user, now);
            userRequestCount.replace(user, 0);
        }
        if(userRequestCount.get(user) < maxRequest){
            userRequestCount.replace(user, userRequestCount.get(user)+1);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        RateLimiter_fixedWindow rateLimit = new RateLimiter_fixedWindow(4, 1000);
        new Thread() {
            public void run() {
                for (int i = 0; i < 15; i++) {
                    try {
                        System.out.println("   # Debjit thread->1 req - " + i + " : " + rateLimit.allowRequest("Debjit"));
                        sleep(200);
                    } catch (InterruptedException ie) {
                        ie.getStackTrace();
                    }
                }
            }
        }.start();

        new Thread() {
            public void run() {
                for (int i = 0; i < 15; i++) {
                    try {
                        System.out.println("   # Debjit thread->2 req - " + i + " : " + rateLimit.allowRequest("Debjit"));
                        sleep(250);
                    } catch (InterruptedException ie) {
                        ie.getStackTrace();
                    }
                }
            }
        }.start();
    }
}
