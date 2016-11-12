package com.github.marcosalis.training.snippets.annotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

/**
 * Simple demonstration on JSR-305 thread-safety annotations.
 * <p>
 * Not thread safe classes are usually all the other classes that are not annotated.
 */
@SuppressWarnings("unused, MismatchedQueryAndUpdateOfCollection")
public class ThreadSafetyExample {

    @Immutable
    // https://docs.oracle.com/javase/tutorial/essential/concurrency/imstrat.html
    public static final class ImmutableClass {

        private final String myString;
        private final String myInt;
        private final List<String> myList;
        // private final Date myDate; // what would happen here?

        public ImmutableClass(String myString, String myInt, List<String> myList) {
            this.myString = myString;
            this.myInt = myInt;
            this.myList = new ArrayList<>(myList); // defensive copy
        }

        // ... other getters

        public List<String> getMyList() {
            return Collections.unmodifiableList(myList);
        }

    }

    @ThreadSafe
    public static class ThreadSafeClass {

        private final ConcurrentMap<String, Long> lastWriteLog = new ConcurrentHashMap<>();

        private final ReentrantReadWriteLock counterLock = new ReentrantReadWriteLock();
        private final ThreadLocal<Long> lastCounterAccess = new ThreadLocal<>();

        @GuardedBy("this")
        private Object myObject;

        @GuardedBy("counterLock")
        private int myCounter;

        public int getMyCounter() {
            counterLock.readLock().lock();
            try {
                return myCounter;
            } finally {
                counterLock.readLock().unlock();
                lastCounterAccess.set(System.currentTimeMillis());
            }
        }

        public synchronized Object getMyObject() {
            return myObject;
        }

        public synchronized void setMyObject(Object myObject) {
            lastWriteLog.put(Thread.currentThread().getName(), System.currentTimeMillis());
            this.myObject = myObject;
        }
    }

}