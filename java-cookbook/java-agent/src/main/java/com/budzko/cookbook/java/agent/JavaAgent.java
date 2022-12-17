package com.budzko.cookbook.java.agent;

import com.budzko.cookbook.java.agent.transformer.CustomTransformer;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class JavaAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("Premain: " + JavaAgent.class.getSimpleName());
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(
                    ClassLoader loader,
                    String className,
                    Class<?> classBeingRedefined,
                    ProtectionDomain protectionDomain,
                    byte[] classfileBuffer
            ) throws IllegalClassFormatException {
                return new CustomTransformer("com.budzko.cookbook.java.agent.JavaAgentApp").transform(
                        loader,
                        className,
                        classBeingRedefined,
                        protectionDomain,
                        classfileBuffer
                );
            }
        });
        System.out.println("Exit: " + JavaAgent.class.getSimpleName());
    }
}
