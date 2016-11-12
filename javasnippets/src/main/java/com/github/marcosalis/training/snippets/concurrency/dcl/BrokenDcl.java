package com.github.marcosalis.training.snippets.concurrency.dcl;

@SuppressWarnings("unused")
public class BrokenDcl {

    private Object singleton = null;

    public Object getSingleton() {
        if (singleton == null) {
            synchronized (this) {
                if (singleton == null) {
                    singleton = new Object();
                }
            }
        }
        return singleton;
    }

}