package com.github.marcosalis.training.snippets.concurrency.exercises;

/**
 * Unique integer identifier generator.
 *
 * NOT thread safe (obviously).
 *
 * Why? <pre>
 * - Thread interference
 * - Memory consistency errors
 * </pre>
 */
@SuppressWarnings("unused")
public class UniqueId {

    private int counter = 0;

    public void increment() {
        counter++;
    }

    public int get() {
        return counter;
    }

}