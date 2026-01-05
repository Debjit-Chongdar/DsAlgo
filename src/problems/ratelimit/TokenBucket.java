package problems.ratelimit;

import java.util.Date;

// Most popular
//refill bucket in a fixed interval or/ refill bucket by each request
public class TokenBucket {

    private double maxRequest;
    // refill rate per second
    private int refillRate; // 5 token per second -> 30 token per min

    private double tokenLeft;
    private long lastRefillTime;

    public TokenBucket(double maxRequest, int refillRate){
        this.refillRate = refillRate;
        this.maxRequest = maxRequest;
        this.tokenLeft = maxRequest;
        this.lastRefillTime = new Date().getTime();
    }

    public synchronized boolean allowRequest(){
        // micro batch will refill the tokens
        refill();
        // if token available then continue
        if(tokenLeft > 0){
            tokenLeft--;
            return true;
        }
        return false;
    }

    private void refill(){
        long now = new Date().getTime();
        // refill rate is in second so convert it to millisecond
        // then find how many token should add by the time spend from last refill time
        double tokensToAdd = (now - lastRefillTime) / 1000.0 * refillRate;
        // if for long time request not being made then tokenToAdd may be greater than maxToken
        tokenLeft = Math.min(tokenLeft + tokensToAdd, maxRequest);
        // update last refill time
        lastRefillTime = now;
    }

    public static void main(String[] args) throws InterruptedException{
        TokenBucket bucket = new TokenBucket(5, 1);

        System.out.println("Sending 7 requests immediately:");
        for (int i = 1; i <= 7; i++) {
            System.out.println("Request " + i + ": " + bucket.allowRequest());
        }

        // wait for tokens to refill
        System.out.println("\nWaiting 3 seconds...");
        Thread.sleep(3000);

        System.out.println("\nSending 3 more requests:");
        for (int i = 1; i <= 3; i++) {
            System.out.println("Request " + i + ": " + bucket.allowRequest());
        }
    }
}
