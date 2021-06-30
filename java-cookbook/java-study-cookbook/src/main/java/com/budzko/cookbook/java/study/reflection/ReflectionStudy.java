package com.budzko.cookbook.java.study.reflection;

import com.budzko.cookbook.java.study.clss.ClassStudy;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @see ClassStudy
 */
public class ReflectionStudy {
    private int privateField;
    private static int privateStaticField;

    public static void main(String[] args) throws Exception {
        changePrivateStaticField();

    }

    private static void changePrivateStaticField() throws NoSuchFieldException, IllegalAccessException {
        Field psf = ReflectionStudy.class.getDeclaredField("privateStaticField");
        System.out.println(Modifier.toString(psf.getModifiers()));
        psf.setAccessible(true);
        psf.set(null, 10);
        System.out.println(privateStaticField);
    }

}
