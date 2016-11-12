package com.github.marcosalis.training.snippets.annotations;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Dealing with null values with nullability annotations and the code inspector.
 */
@SuppressWarnings("WeakerAccess, unused")
public class NullabilityAnnotationsDemo {

    public static class A {

        private @Nullable Object myObject;

        public @Nullable Object getMyObject() {
            return myObject;
        }

        public void setMyObject(@NonNull Object myObject) {
            this.myObject = myObject;
        }

        public boolean doSomething() {
            if (myObject != null) {
                new B(myObject).doSomethingElse();
                return true;
            }
            return false;
        }
    }

    public static class B {

        private final Object myObject;

        public B(@NonNull Object myObject) {
            this.myObject = myObject;
        }

        public void doSomethingElse() {
            new C().doSomeOtherStuff(myObject);
        }
    }

    public static class C {

        /**
         * Prints on the standard output the class name of the passed object.
         */
        public void doSomeOtherStuff(@NonNull Object obj) {
            System.out.println(obj.getClass().getName());
        }
    }

}