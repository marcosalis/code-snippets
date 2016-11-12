package com.github.marcosalis.training.snippets.annotations;

/**
 * Dealing with null values without nullability annotations.
 */
@SuppressWarnings("WeakerAccess, unused")
public class NullabilityNoAnnotationsDemo {

    public static class A {

        // this can be null
        private Object myObject;

        public Object getMyObject() {
            return myObject;
        }

        public void setMyObject(Object myObject) {
            this.myObject = myObject;
        }

        public boolean doSomething() {
            return new B(myObject).doSomethingElse();
        }
    }

    public static class B {

        private final Object myObject;

        public B(Object myObject) {
            this.myObject = myObject;
        }

        /**
         * Prints on the standard output the class name of the wrapped object, if not null.
         *
         * @return true if the object was not null and it was possible to print its class, false
         * otherwise
         */
        public boolean doSomethingElse() {
            if (myObject != null) {
                C c = new C();
                return c.doSomeOtherStuff(myObject);
            }
            return false;
        }
    }

    public static class C {

        /**
         * Prints on the standard output the class name of the passed object, if not null.
         *
         * @param obj The Object or null
         * @return true if the object was not null and it was possible to print its class, false
         * otherwise
         */
        public boolean doSomeOtherStuff(Object obj) {
            if (obj != null) {
                System.out.println(obj.getClass().getName());
                return true;
            }
            return false;
        }
    }

}