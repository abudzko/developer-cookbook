package com.budzko.cookbook.java.study.generics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericsStudy {
    public static void main(String[] args) {
        extendsGenerics();
        superGenerics();
        methodSuperGenerics();
        methodExtendsGenerics();
        extendsWildCardGenerics();
        explicitType();
        arrayOfGenericClass();
        genericArray();
        getGenericArrayClassCastException();
        genericsExtendsTwoInterfaces();
        unboundWildcard();
        f1();
        recurringGeneric();
        throwGenericEx();
        genericListCast();
        genericListSubClassCast();
    }

    private static void genericListSubClassCast() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        Object o = list;
        List<Number> list2 = (List<Number>) o;
        System.out.println(list2.get(0));//safe operation as Integer is subclass on Number
    }

    private static void genericListCast() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        List<String> list1 = new ArrayList<>();

//        list1 = list;//illegal operation
        System.out.println(list.getClass() == list1.getClass());//true

        Object o = list;
        Object o1 = list1;
        List<String> list2 = (List<String>) o;
//        System.out.println(list2.get(0));//java.lang.ClassCastException: class java.lang.Integer cannot be cast to class java.lang.String
        o = o1;
        System.out.println(list.getClass());
        System.out.println(list1.getClass());
    }

    private static void throwGenericEx() {
        class TrExImpl implements TrEx<FileNotFoundException> {
            @Override
            public void foo() throws FileNotFoundException {
                throw new FileNotFoundException();
            }
        }
    }

    interface TrEx<E extends IOException> {
        void foo() throws E;
    }

    private static void recurringGeneric() {

        class A<T> {
            T t;

            public T get() {
                return t;
            }

            public void set(T t) {
                this.t = t;
            }

            public A<T> getThis() {
                return this;
            }
        }
        class B extends A<B> {
        }

        A<String> objectA = new A<>();
        Class<? extends A> aClass = objectA.getClass();

        B b = new B();
        b.set(new B());
        B b1 = b.get();//exact return type is B

        class X<T extends X<T>> {
            T t;

            public T get() {
                return t;
            }

            public void set(T t) {
                this.t = t;
            }

        }

        class Y<T> extends X<Y<T>> {
        }
        Y<String> y = new Y<>();
        Y<String> stringY = y.get();

    }

    private static void f1() {
        class Holder<T> {
            T t;

            void set(T t) {
                this.t = t;
            }

            public T getT() {
                return t;
            }
        }
        class Executor {
            void foo(Holder<List<?>> h) {
                List<?> t = h.getT();
                for (Object o : t) {
                    System.out.println(o);
                }
                List<String> list = new ArrayList<>();
                h.set(list);
            }
        }

        Executor executor = new Executor();
        Holder<List<?>> holder = new Holder<>();
        ArrayList<String> list = new ArrayList<>();
        list.add("v1");
        holder.set(list);
        executor.foo(holder);
    }

    private static void unboundWildcard() {
        class A {
            List<?> getStringList() {
                return new ArrayList<String>();
            }

            List<?> getIntegerList() {
                ArrayList<Integer> integers = new ArrayList<>();
                integers.add(1);
                integers.add(2);
                return integers;
            }
        }

        List<?> integerList = new A().getIntegerList();
        for (Object o : integerList) {
            System.out.println(((Integer) o).intValue());
        }

        List<?> list = new ArrayList<>();
//        list.add(new Object());
    }

    interface I1 {
        void fooI1();

    }

    interface I2 {
        void fooI2();
    }

    private static void genericsExtendsTwoInterfaces() {
        class A implements I1, I2 {
            @Override
            public void fooI1() {
            }

            @Override
            public void fooI2() {
            }

        }
        class G<T extends A & I1 & I2> {
            T t;

            void foo() {
                t.fooI1();
                t.fooI2();
            }

            public T getT() {
                return t;
            }

            public void setT(T t) {
                this.t = t;
            }
        }

        G<A> ga = new G<>();
        ga.setT(new A());
    }


    private static void getGenericArrayClassCastException() {
        class G<T> {
            private T[] arr;

            @SuppressWarnings({"unchecked"})
            G() {
                this.arr = (T[]) new Object[1];
            }

            T[] getArr() {
                return arr;
            }
        }
        G<Integer> ig = new G<>();
//        Integer[] arr1 = ig.getArr();//java.lang.ClassCastException:!
        Object[] arr1 = ig.getArr();
        System.out.println(arr1.getClass());

        class GG<T> {
            private T[] arr;

            @SuppressWarnings({"unchecked"})
            GG(Class<T> clss) {
                this.arr = (T[]) Array.newInstance(clss, 1);
            }


            T[] getArr() {
                return arr;
            }
        }
        new ArrayList<Integer>(new ArrayList<>());

        GG<Integer> gg = new GG<>(Integer.class);
        Integer[] arr2 = gg.getArr();
        System.out.println(arr2.getClass());
    }

    private static void genericArray() {
        class G<T> {
            T[] arr;

            @SuppressWarnings("unchecked")
            G() {
//                arr = new T[1];
                this.arr = (T[]) new Object[1];
            }

            void set(T t) {
                this.arr[0] = t;
            }

            T get() {
                return this.arr[0];
            }
        }
        G<String> sg = new G<>();
        sg.set("value");
        System.out.println(sg.get());
    }

    private static void arrayOfGenericClass() {
        //The only way to successfully create an array of a generic type is to
        //create a new array of the erased type, and cast that
        class G<T> {
            private T t;

            G(T t) {
                this.t = t;
            }

            @Override
            public String toString() {
                return t.toString();
            }
        }
        @SuppressWarnings({"unchecked"})
        G<String>[] gs = (G<String>[]) new G[1];
        gs[0] = new G<>(G.class.getName());
        System.out.println(gs[0]);
    }

    private static void explicitType() {
        GenericsStudy.<String, String>map();//without assigned, for the case to pass in method.
        Map<String, String> map = map();//with assigned
    }

    private static <K, V> Map<K, V> map() {
        return new HashMap<>();
    }

    private static List<? extends X> methodExtendsGenerics() {
        ArrayList<Y> result = new ArrayList<>();// Y extends X
        return result;
    }

    private static void extendsWildCardGenerics() {
        class B {
        }
        class C extends B {
        }
        class A<T> {
            T t;
        }
        A<? extends B> objectA = new A<>();
//        objectA.t = new C();//not compiled
    }

    private static List<? super Y> methodSuperGenerics() {
        ArrayList<X> result = new ArrayList<>();// X is supper of Y
        return result;
    }

    static class X {
    }

    static class Y extends X {
    }

    static class Z extends Y {
    }

    private static void superGenerics() {
        class A {
        }
        class B extends A {
        }
        class C extends B {
        }
        List<? super B> list1 = new ArrayList<>();
        C c = new C();//element should extends B or be B. B should be a super class of a element class
        list1.add(c);

        List<? extends B> list2 = new ArrayList<C>();// "?" - unknown class type, we can't add anything in such list expect null
        //
        B b = new B();
//
//        list2.add(new Object());
//        list2.add(b);
        list2.add(null);
        B b1 = list2.get(0);//but list2 object can return B

        class G<E> {
            E e;

            public G(E e) {
                this.e = e;
            }

            void set(E e) {
                this.e = e;
            }

            E get() {
                return e;
            }
        }
        C c1 = new C();
        G<? extends B> g = new G<C>(c1);
//        g.set(c1);
        System.out.println("g.get().getClass().getSimpleName():" + g.get().getClass().getSimpleName());//return C

    }

    private static void extendsGenerics() {
        class TwoTuple<A, B> {
            private A a;
            private B b;

            void addA(A a) {
                this.a = a;
            }

            void addB(B a) {
                this.b = b;
            }

            public A getA() {
                return a;
            }

            public B getB() {
                return b;
            }
        }

        class ThreeTuple<A, B, C> extends TwoTuple<A, B> {
            private C c;

            void addC(C c) {
                this.c = c;
            }

            public C getC() {
                return c;
            }
        }

        ThreeTuple<String, Long, Integer> threeTuple = new ThreeTuple<>();
        threeTuple.addA("");
        threeTuple.addB(2L);
        threeTuple.addC(1);
    }

}
