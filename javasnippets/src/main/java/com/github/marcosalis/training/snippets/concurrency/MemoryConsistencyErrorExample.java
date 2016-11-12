package com.github.marcosalis.training.snippets.concurrency;

@SuppressWarnings("unused")
public class MemoryConsistencyErrorExample {

    private int c = 0;

    // called by thread A
    public void increment() {
        c++;
    }

    // called by thread B shortly afterwards
    public void println() {
        System.out.println(c);
    }

}