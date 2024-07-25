package com.wangguangwu.ratelimit;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Fixed Window Counter.
 *
 * @author wangguangwu
 */
public class FixedWindowCounter {

    private final int limit;
    private final long windowSize;
    private final AtomicInteger counter;
    private long windowStart;

    public FixedWindowCounter(int limit, long windowSize) {
        this.limit = limit;
        this.windowSize = windowSize;
        this.counter = new AtomicInteger(0);
        this.windowStart = System.currentTimeMillis();
    }

    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();
        if (now - windowStart > windowSize) {
            counter.set(0);
            windowStart = now;
        }

        return counter.incrementAndGet() <= limit;
    }

    public static void main(String[] args) {
        // Set the counter
        FixedWindowCounter counter = new FixedWindowCounter(5, 1000);

        // Simulate request
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    if (counter.allowRequest()) {
                        System.out.println(Thread.currentThread().getName() + ": Request allowed at " + System.currentTimeMillis());
                    } else {
                        System.out.println(Thread.currentThread().getName() + ": Request denied at " + System.currentTimeMillis());
                    }

                    try {
                        TimeUnit.MICROSECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }).start();
        }
    }
}
