package com.github.marcosalis.training.snippets.concurrency;

public class ThreadInterferenceExample {

    private int c = 0;

    public void increment() { // called by thread A
        c++;
    }

    public void decrement() { // called by thread B
        c--;
    }

    public int value() { // what are the possible values of c?
        return c;
    }

}