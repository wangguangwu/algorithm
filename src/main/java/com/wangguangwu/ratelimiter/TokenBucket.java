package com.wangguangwu.ratelimiter;

import java.util.concurrent.TimeUnit;

/**
 * Token Bucket.
 *
 * @author wangguangwu
 */
public class TokenBucket {

    private final int capacity;
    private final int rate;
    private int tokens;
    private long lastTokenTime;

    public TokenBucket(int capacity, int rate) {
        this.capacity = capacity;
        this.rate = rate;
        this.tokens = capacity;
        this.lastTokenTime = System.currentTimeMillis();
    }

    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();
        long elapsedTime = now - lastTokenTime;
        int newTokens = (int) (elapsedTime * rate / 1000);
        tokens = Math.min(capacity, tokens + newTokens);
        lastTokenTime = now;
        if (tokens > 0) {
            tokens--;
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        // Set the token bucket
        TokenBucket bucket = new TokenBucket(5, 1);

        // Simulate requests
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    if (bucket.allowRequest()) {
                        System.out.println(Thread.currentThread().getName() + ": Request allowed at " + System.currentTimeMillis());
                    } else {
                        System.out.println(Thread.currentThread().getName() + ": Request denied at " + System.currentTimeMillis());
                    }

                    try {
                        // Simulate request interval
                        TimeUnit.MILLISECONDS.sleep(200);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }).start();
        }
    }
}
