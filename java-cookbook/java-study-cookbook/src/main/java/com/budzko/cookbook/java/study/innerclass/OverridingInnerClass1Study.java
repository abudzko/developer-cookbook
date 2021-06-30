package com.budzko.cookbook.java.study.innerclass;

/**
 * What is the output?
 * <p>
 * Response:
 * New Egg()
 * Egg.Yolk()
 * BigEgg.Yolk()
 *
 * New Egg()
 * Egg.Yolk()
 * Egg.Yolk()
 */
public class OverridingInnerClass1Study {

    public static void main(String[] args) {
        OverridingInnerClass1Study overridingInnerClass = new OverridingInnerClass1Study();

        //Case:1
        BigEgg bigEgg = overridingInnerClass.new BigEgg();
        bigEgg.new Yolk();

        System.out.println();

        //Case:2
        Egg egg = overridingInnerClass.new BigEgg();
        egg.new Yolk();
    }

    class Egg {
        private Yolk y;

        public Egg() {
            print("New Egg()");
            y = new Yolk();
        }

        public class Yolk {
            public Yolk() {
                print("Egg.Yolk()");
            }
        }
    }

    class BigEgg extends Egg {

        public class Yolk {
            public Yolk() {
                print("BigEgg.Yolk()");
            }
        }
    }

    private void print(String s) {
        System.out.println(s);
    }
}
