package com.budzko.cookbook.java.study.classloader;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ClassLoaderStudy {
    public static void main(String[] args) {
        classLoaderHierarchy();
        loadClassFromBytes();
        differentClassLoaders();
    }

    private static void differentClassLoaders() {
        CustomClassLoader customClassLoader1 = new CustomClassLoader();
        CustomClassLoader customClassLoader2 = new CustomClassLoader();
        try {
            Class<?> aClass1 = customClassLoader1.loadClass(CustomClassLoader.EXTERNAL_CLASS_NAME);
            Class<?> aClass2 = customClassLoader2.loadClass(CustomClassLoader.EXTERNAL_CLASS_NAME);
            Object o1 = aClass1.getDeclaredConstructor().newInstance();
            Object o2 = aClass2.getDeclaredConstructor().newInstance();

            System.out.println(o1 + ":" + o1.getClass().getClassLoader());
            System.out.println(o2 + ":" + o2.getClass().getClassLoader());

            Method[] declaredMethods = o1.getClass().getDeclaredMethods();
            Method printMethod = declaredMethods[0];
            printMethod.invoke(o2, "Hello");//java.lang.IllegalArgumentException: object is not an instance of declaring class

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadClassFromBytes() {

        CustomClassLoader customClassLoader = new CustomClassLoader();
        try {
            Class<?> aClass = customClassLoader.loadClass(CustomClassLoader.EXTERNAL_CLASS_NAME);
            Object o = aClass.getDeclaredConstructor().newInstance();
            Method[] declaredMethods = o.getClass().getDeclaredMethods();
            Method printMethod = declaredMethods[0];
            printMethod.invoke(o, "Hello");

            printClassLoaderHierarchy(aClass.getClassLoader());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void classLoaderHierarchy() {
        ClassLoader classLoader = new CustomClassLoader();
        printClassLoaderHierarchy(classLoader);
    }

    private static void printClassLoaderHierarchy(ClassLoader classLoader) {
        while (classLoader != null) {
            System.out.println(classLoader + ": was loaded by " + classLoader.getClass().getClassLoader() + " class loader");
            classLoader = classLoader.getParent();
        }
    }
}

class CustomClassLoader extends ClassLoader {
    static String EXTERNAL_CLASS_NAME = "com.budzko.cookbook.java.study.classloader.Printer";
    private static final String PATH_TO_CLASS_BYTES
            = "./java-cookbook/java-study-cookbook/src/main/resources/classloader/Printer_class_bytes";

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if ("com.budzko.cookbook.java.study.classloader.Printer".equals(name)) {
            try {
                byte[] bytes = Files.readAllBytes(Paths.get(PATH_TO_CLASS_BYTES));
                return defineClass(name, bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return super.findClass(name);
    }
}
