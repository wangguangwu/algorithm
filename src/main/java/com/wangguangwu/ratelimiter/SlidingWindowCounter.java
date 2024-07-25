package com.wangguangwu.ratelimiter;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * Sliding window counter.
 *
 * @author wangguangwu
 */
public class SlidingWindowCounter {

    private final int limit;
    private final int windowSize;
    private final Queue<Long> queue;

    public SlidingWindowCounter(int limit, int windowSize) {
        this.windowSize = windowSize;
        this.limit = limit;
        this.queue = new LinkedList<>();
    }

    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();
        while (!queue.isEmpty() && now - queue.peek() > windowSize) {
            queue.poll();
        }

        if (queue.size() < limit) {
            queue.add(now);
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        // Set the counter
        SlidingWindowCounter counter = new SlidingWindowCounter(5, 1000);

        // Simulate requests
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    if (counter.allowRequest()) {
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
