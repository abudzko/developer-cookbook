package com.budzko.cookbook.java.agent.transformer;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.LoaderClassPath;
import javassist.bytecode.MethodInfo;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.Arrays;

public class CustomTransformer implements ClassFileTransformer {
    private final String targetClassName;
    private final String jsClassName;

    public CustomTransformer(String targetClassName) {
        this.targetClassName = targetClassName;
        this.jsClassName = targetClassName.replaceAll("\\.", "/");
        ;
    }

    @Override
    public byte[] transform(
            ClassLoader loader,
            String className,
            Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain,
            byte[] classfileBuffer
    ) {
        byte[] byteCode = classfileBuffer;
        if (!jsClassName.equals(className)) {
            return classfileBuffer;
        }
        System.out.println("[Agent] class name = " + className);
        System.out.println("[Agent] class loader = " + loader);
        try {
            ClassPool cp = ClassPool.getDefault();
            cp.appendClassPath(new LoaderClassPath(loader));
            CtClass cc = cp.get(targetClassName);
            Arrays.stream(cc.getDeclaredMethods()).forEach(ctMethod -> System.out.println(ctMethod.getName()));
            CtMethod m = cc.getDeclaredMethod("sayHello");

            MethodInfo mi = m.getMethodInfo();
            int lineNumber = mi.getLineNumber(0);
            System.out.println(mi.getName() + " " + lineNumber);

            m.addLocalVariable("startTime", CtClass.longType);
            m.addLocalVariable("endTime", CtClass.longType);
            m.insertAt(15, "startTime = System.currentTimeMillis();");
            m.insertAt(25, "endTime = System.currentTimeMillis();" +
                    "System.out.println(\"Elapsed time = \" + (endTime - startTime) + \" ms\");");
            System.out.println("[Agent] Transforming class " + targetClassName);
            byteCode = cc.toBytecode();
        } catch (Throwable e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return byteCode;
    }
}
