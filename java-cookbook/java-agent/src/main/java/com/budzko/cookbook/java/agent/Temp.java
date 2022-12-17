package com.budzko.cookbook.java.agent;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

import java.util.Arrays;

public class Temp {
    public static void main(String[] args) throws NotFoundException {
        System.out.println(Temp.class.getName());
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("com.budzko.cookbook.java.agent.JavaAgent");
        Arrays.stream(cc.getMethods()).forEach(ctMethod -> System.out.println(ctMethod.getName()));
    }
}
