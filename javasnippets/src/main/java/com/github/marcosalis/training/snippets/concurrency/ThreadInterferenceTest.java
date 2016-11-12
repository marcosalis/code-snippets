package com.github.marcosalis.training.snippets.concurrency;

import junit.framework.TestCase;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;

import java.util.Locale;
import java.util.concurrent.CountDownLatch;

/**
 * Simple demonstration on how thread interference can affect multi-threaded computations.
 */
public class ThreadInterferenceTest extends TestCase {

    private int i, j = 0;

    public void testSynchronizationFailure() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(2);

        Thread thread1 = new Thread(new IncrementRunnable(latch));
        Thread thread2 = new Thread(new IncrementRunnable(latch));

        long start = System.nanoTime();

        thread1.start();
        thread2.start();

        latch.await();

        long stop = System.nanoTime();

        System.out.println(String.format(Locale.US, "Test took %f ms", (double) (stop - start) / 1000000));

        System.out.println("i = " + i);
        System.out.println("j = " + j);
        Assert.assertThat(i, CoreMatchers.is(j));
    }

    // private synchronized void increment() {
    private void increment() {
        i++;
        j++;
    }

    private class IncrementRunnable implements Runnable {
        private final CountDownLatch latch;

        IncrementRunnable(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            int counter = 1000;
            while (counter > 0) {
                increment();
                counter--;
            }
            latch.countDown();
        }
    }

}