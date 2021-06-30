package com.budzko.cookbook.java.study.clss;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ClassStudy {

    public static void main(String[] args) throws Exception {
        singleClassObject();
        changePrivateFinalField();
        changePrivateStaticFinalField();
        primitiveTypeClass();
        classOfAnySubtype();
        proxyObject();
    }

    interface Worker {
        void startWork();

        void stopWork();
    }

    static class ProxyHandler implements InvocationHandler {

        private final Object realInstance;

        ProxyHandler(Object realInstance) {
            this.realInstance = realInstance;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(
                    "Class:" + realInstance.getClass().getSimpleName()
                            + ", method:" + method.getName() + ", arguments:"
                            + Arrays.toString(args)
            );
            return method.invoke(realInstance, args);
        }
    }

    static class FarmWorker implements Worker {
        @Override
        public void startWork() {
            System.out.println(getClass().getSimpleName() + " started to work.");
        }

        @Override
        public void stopWork() {
            System.out.println(getClass().getSimpleName() + " stopped to work.");
        }
    }

    static class PlantWorker implements Worker {
        @Override
        public void startWork() {
            System.out.println(getClass().getSimpleName() + " started to work.");
        }

        @Override
        public void stopWork() {
            System.out.println(getClass().getSimpleName() + " stopped to work.");
        }
    }

    private static void proxyObject() {
        FarmWorker farmWorker = new FarmWorker();
        PlantWorker plantWorker = new PlantWorker();
        Worker farmWorkerProxy = (Worker) getProxy(farmWorker);
        farmWorkerProxy.startWork();
        farmWorkerProxy.stopWork();

        System.out.println("farmWorkerProxy instanceof Worker: " + (farmWorkerProxy instanceof Worker));

        Worker plantWorkerProxy = (Worker) getProxy(plantWorker);
        plantWorkerProxy.startWork();
        plantWorkerProxy.stopWork();
    }

    private static <T> Object getProxy(T realInstance) {
        return Proxy.newProxyInstance(
                realInstance.getClass().getClassLoader(),
                realInstance.getClass().getInterfaces(),
                new ProxyHandler(realInstance)
        );
    }

    private static void classOfAnySubtype() throws Exception {
//        Class<Number> genericNumberClassIllegal = Integer.class;
//        //illegal operation Class<Number> is not parent of Class<Integer>, but Number is parent of Integer
        Class<? extends Number> genericNumberClass = Integer.class;
        genericNumberClass = Integer.class;

        Class<? super Integer> genericIntegerClass = Number.class;

        Class<Integer> integerClass = Integer.class;
        Class<? super Integer> superClass = integerClass.getSuperclass();
        System.out.println(superClass);

        Class<? super B> classA = A.class;
        System.out.println("classA.getDeclaredConstructor().newInstance(): " + classA.getDeclaredConstructor().newInstance());
        Class<B> classB = B.class;
        Class<? super B> superclassB = classB.getSuperclass();
        System.out.println("superclassB.getDeclaredConstructor().newInstance(): " + superclassB.getDeclaredConstructor().newInstance());
    }

    private static void primitiveTypeClass() {
        System.out.println("boolean.class == Boolean.class: " + (boolean.class == Boolean.class));
        System.out.println("boolean.class == Boolean.TYPE: " + (boolean.class == Boolean.TYPE));
        System.out.println("boolean.class == ((Boolean) true).getClass(): " + (boolean.class == ((Boolean) true).getClass()));
        consume_Boolean(true);
        consume_Boolean(Boolean.TRUE);
        consume_boolean(true);
        consume_boolean(Boolean.TRUE);
    }

    private static void consume_Boolean(Boolean v) {
        System.out.println("consume_Boolean(Boolean v) - v.getClass().hashCode(): " + v.getClass().hashCode());
        System.out.println("consume_Boolean(Boolean v): boolean.class == v.getClass(): " + (boolean.class == v.getClass()));
        System.out.println("consume_Boolean(Boolean v): Boolean.class == v.getClass(): " + (Boolean.class == v.getClass()));
    }

    private static void consume_boolean(boolean v) {
        System.out.println("consume_boolean(boolean v) - boolean.class == ((Boolean) v).getClass(): " + (boolean.class == ((Boolean) v).getClass()));
    }

    private static void changePrivateStaticFinalField() throws Exception {
        Field modifierField = Field.class.getDeclaredField("modifiers");
        modifierField.setAccessible(true);

        Class<?> clss = Class.forName("com.budzko.cookbook.java.study.clss.TestClass");

        Field staticFinalField = clss.getDeclaredField("staticFinalField");

        System.out.println(Modifier.toString(modifierField.getModifiers()));
        System.out.println(Modifier.toString(staticFinalField.getModifiers()));

        modifierField.setInt(staticFinalField, staticFinalField.getModifiers() & ~Modifier.FINAL);
        System.out.println(Modifier.toString(modifierField.getModifiers()));
        System.out.println(Modifier.toString(staticFinalField.getModifiers()));

        staticFinalField.setAccessible(true);

        TestClass testClass = (TestClass) clss.getConstructor().newInstance();
        staticFinalField.set(null, "new static final field value");
        System.out.println("Not changed because was inlined, see bytecode: " + testClass.getStaticFinalField());
        System.out.println("Field value was changed: " + staticFinalField.get(testClass));

        //restore state
        staticFinalField.setAccessible(false);
        modifierField.setInt(staticFinalField, staticFinalField.getModifiers() | Modifier.FINAL);
        modifierField.setAccessible(false);

        System.out.println(Modifier.toString(modifierField.getModifiers()));
        System.out.println(Modifier.toString(staticFinalField.getModifiers()));
        System.out.println();
    }

    private static void changePrivateFinalField() throws NoSuchFieldException, IllegalAccessException {
        TestClass testClass = new TestClass();
        Field finalField = testClass.getClass().getDeclaredField("finalField");
        finalField.setAccessible(true);
        finalField.set(testClass, "new final field value");
        System.out.println("Not changed because was inlined, see bytecode: " + testClass.getFinalField());
    }

    private static void singleClassObject() {
        String str1 = "abc";
        String str2 = "123";
        System.out.println("str1.getClass() == str2.getClass():" + (str1.getClass() == str2.getClass()));
    }
}

class TestClass {

    private static final String staticFinalField = "static final field value";
    private final String finalField = "final field value";

    public TestClass() {
    }

    public String getStaticFinalField() {
        return staticFinalField;
    }

    public String getFinalField() {
        return finalField;
    }
}

class A {
    A() {
    }
}

class B extends A {
}