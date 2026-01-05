package problems.ratelimit;

import java.util.*;

import static java.lang.Thread.sleep;

//Most accurate
public class RateLimiter_SlidingWindow {
    private long windowSize;
    int maxRequest;
    private Map<String, Queue<Long>> userRequests;

    public RateLimiter_SlidingWindow(int maxRequest, long windowSize) {
        this.maxRequest = maxRequest;
        this.windowSize = windowSize;
        this.userRequests = new HashMap<>();
    }

    public synchronized boolean allowRequest(String userid) {
        userRequests.putIfAbsent(userid, new LinkedList<>());
        //current request timestamp
        long now = new Date().getTime();
        System.out.print("time : " + now);
        // stored timestamp for the same user
        Queue<Long> stored_timestamp = userRequests.get(userid);
        // remove expired timestamp
        while (!stored_timestamp.isEmpty() && now - stored_timestamp.peek() > windowSize) {
            stored_timestamp.poll();
        }
        //if within the time frame requests size is less than the threshold then allow else reject
        if (stored_timestamp.size() < maxRequest) {
            stored_timestamp.offer(now);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        RateLimiter_SlidingWindow rateLimit = new RateLimiter_SlidingWindow(4, 1000);
        new Thread() {
            public void run() {
                for (int i = 0; i < 15; i++) {
                    try {
                        System.out.println("   # Debjit thread->1 req - " + i + " : " + rateLimit.allowRequest("Debjit"));
                        sleep(300);
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
                        sleep(200);
                    } catch (InterruptedException ie) {
                        ie.getStackTrace();
                    }
                }
            }
        }.start();
    }
}
