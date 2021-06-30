package com.budzko.cookbook.java.study.innerclass;

/**
 * What is the output?
 * <p>
 * Response:
 * Egg2.Yolk()
 * New Egg2()
 * Egg2.Yolk()
 * BigEgg2.Yolk()
 * BigEgg2.Yolk.f()
 */
public class OverridingInnerClass2Study {
    public static void main(String[] args) {
        OverridingInnerClass2Study overridingInnerClass = new OverridingInnerClass2Study();
        Egg2 e2 = overridingInnerClass.new BigEgg2();
        e2.g();
    }

    class Egg2 {

        private Yolk yolk = new Yolk();

        public Egg2() {
            print("New Egg2()");
        }

        public void insertYolk(Yolk yolk) {
            this.yolk = yolk;
        }

        public void g() {
            yolk.f();
        }

        public class Yolk {

            public Yolk() {
                print("Egg2.Yolk()");
            }

            public void f() {
                print("Egg2.Yolk.f()");
            }
        }
    }

    class BigEgg2 extends Egg2 {

        public BigEgg2() {
            insertYolk(new Yolk());
        }

        public class Yolk extends Egg2.Yolk {

            public Yolk() {
                print("BigEgg2.Yolk()");
            }

            public void f() {
                print("BigEgg2.Yolk.f()");
            }
        }
    }

    private void print(String s) {
        System.out.println(s);
    }
}
