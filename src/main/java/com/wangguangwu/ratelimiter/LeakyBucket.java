package com.wangguangwu.ratelimiter;

import java.util.concurrent.TimeUnit;

/**
 * Leaky bucket.
 *
 * @author wangguangwu
 */
public class LeakyBucket {

    private final int capacity;
    private final int rate;
    private int water;
    private long lastLeakTime;

    public LeakyBucket(int capacity, int rate) {
        this.capacity = capacity;
        this.rate = rate;
        this.water = 0;
        this.lastLeakTime = System.currentTimeMillis();
    }

    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();
        long elapsedTime = now - lastLeakTime;
        int leaked = (int) (elapsedTime * rate / 1000);

        water = Math.max(0, water - leaked);
        lastLeakTime = now;
        if (water < capacity) {
            water++;
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        // Set the leaky bucket
        LeakyBucket bucket = new LeakyBucket(5, 1);

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
