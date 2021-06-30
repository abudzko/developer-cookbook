package com.budzko.cookbook.java.study.exception;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ExceptionStudy {
    public static void main(String[] args) throws Throwable {
//        fillInStackTrace();
//        rethrowTheSameException();
//        wrapAndRethrowException();
//        throwNullCause();
//        initCause();
//        exHierarchy();
//        catchOrThrowChecked();
//        finallyReturn();
//        finallyThrow();
//        finallyExit0();
//        finallyOutOffMemory();
//        overrideMethod();
//        loseException1();
        loseException2();
    }

    private static void loseException1() {
        class MyClass {
            MyClass() throws FileNotFoundException {
                throw new FileNotFoundException();
            }

            void foo() {
            }
        }

        MyClass myClass = null;
        try {
            myClass = new MyClass();
        } catch (Exception e) {
            myClass.foo();//FileNotFoundException will be lost due to NPE rethrowing
        }
    }

    private static void loseException2() {
        class MyClass {
            MyClass() throws FileNotFoundException {
                throw new FileNotFoundException();
            }

            void foo() {
            }
        }

        MyClass myClass = null;
        try {
            myClass = new MyClass();
        } finally {
            return;////FileNotFoundException will be lost due to return in finally
        }

    }

    private static void overrideMethod() {
        class Parent {
            Parent() throws Exception {
            }

            void foo() throws Throwable {
            }
        }
        class Child extends Parent {
            Child() throws Throwable {// if parent constructor throws checked exception then child class should throw checked exception too
            }

            @Override
            void foo() throws Exception {
            }
        }

        Parent p = null;
        try {
            p = new Child();//Constructor of child class can throw more general exception
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        try {
            p.foo();//Child class can't throw more general exception then parent class.
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private static void finallyOutOffMemory() {
        try {
            ArrayList<Object> objects = new ArrayList<>();
            for (int i = 0; i < Math.pow(10, 10); i++) {
                objects.add(i);
            }
        } finally {
            System.out.println("finallyOutOffMemory");//Will be printed
        }
    }

    private static void finallyExit0() {
        try {
            System.exit(0);
        } finally {
            System.out.println("finallyExit0");//Will not be printed
        }
    }

    private static void finallyThrow() throws Exception {
        try {
            throw new Exception();
        } finally {
            System.out.println("finallyThrow");//Will be printed
        }
    }

    private static void finallyReturn() {
        try {
            return;
        } finally {
            System.out.println("finallyReturn");//Will be printed
        }
    }

    private static void catchOrThrowChecked() throws Throwable {
        try {
            Exception exception = new Exception();
            throw exception;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        Throwable throwable = new Throwable();
        throw throwable;
    }

    private static void exHierarchy() {
        //checked:

        //public class Throwable implements Serializable {
        Throwable throwable = new Throwable();
        try {
            throw throwable;
        } catch (Throwable e) {
            e.printStackTrace();
        }

        //public class Exception extends Throwable {
        Exception exception = new Exception();
        try {
            throw exception;
        } catch (Exception e) {
            e.printStackTrace();
        }

        //unchecked:

        //public class RuntimeException extends Exception {
        RuntimeException runtimeException = new RuntimeException();
        //  throw runtimeException;

        //public class Error extends Throwable {
        Error error = new Error();
        throw error;
    }

    private static void initCause() {
        try {
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new NullPointerException().initCause(e);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static void throwNullCause() {
        try {
            Exception exception = new Exception();
            throw exception.getCause();
        } catch (Throwable e) {
            e.printStackTrace();//java.lang.NullPointerException, not java.lang.Exception, because NPE 'throw null'
        }
    }

    private static void rethrowTheSameException() {
        try {
            try {
                try {
                    NullPointerException nullPointerException = new NullPointerException();
                    throw nullPointerException;
                } catch (NullPointerException e) {
                    throw e;
                }
            } catch (NullPointerException e) {
                throw e;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void wrapAndRethrowException() {
        try {
            try {
                try {
                    NullPointerException nullPointerException = new NullPointerException();
                    throw nullPointerException;
                } catch (NullPointerException e) {
                    throw e;
                }
            } catch (NullPointerException e) {
                throw new Exception(e);
//                throw new RuntimeException(e);
//                throw new Error(e);
//                throw IOException(e);// is not possible to wrap
            }

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static class MyEx extends Throwable {
        @Override
        public synchronized Throwable fillInStackTrace() {
            System.out.println("Calling:" + getClass().getName() + "#fillInStackTrace");//Filling is called from constructor
            return super.fillInStackTrace();
        }
    }

    private static void fillInStackTrace() {
        MyEx myEx = new MyEx();
    }
}
