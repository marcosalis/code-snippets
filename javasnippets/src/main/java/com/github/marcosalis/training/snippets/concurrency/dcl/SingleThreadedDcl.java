package com.github.marcosalis.training.snippets.concurrency.dcl;

public class SingleThreadedDcl {

    private Object singleton = null;

    public Object getSingleton() {
        if (singleton == null) {
            singleton = new Object();
        }
        return singleton;
    }

}