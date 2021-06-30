package com.budzko.cookbook.java.study.inheritance;

public class InheritanceStudy {
    public static void main(String[] args) {
        overloadMethod();
        overrideMethod();
    }

    private static void overrideMethod() {
        class A {
        }
        class B extends A {
        }
        class X {
            void set(A v) {
            }
        }
        class Y extends X {
            @Override
            void set(A v) {
                super.set(v);
            }

            void set(B v) {
            }
        }

    }

    private static void overloadMethod() {
        class A {
        }
        class B extends A {
        }
        class X {
            A get() {
                return new A();
            }
        }
        class Y extends X {
            @Override
            B get() {
                return new B();
            }
        }
    }
}
