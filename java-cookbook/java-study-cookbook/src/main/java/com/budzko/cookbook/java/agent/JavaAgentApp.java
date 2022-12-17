package com.budzko.cookbook.java.agent;

/**
 * @See java-agent project
 * mvn package - to copy java-agent.jar into target dir and compile JavaAgentApp class
 * java -javaagent:target/java-agent.jar -cp target/classes com.budzko.cookbook.java.agent.JavaAgentApp
 */
public class JavaAgentApp {
    public static void main(String[] args) throws InterruptedException {
        JavaAgentApp javaAgentApp = new JavaAgentApp();
        javaAgentApp.start();
    }

    private void start() throws InterruptedException {
        sayHello();
    }

    private static void sayHello() throws InterruptedException {
        System.out.println("Hello!");
        Thread.sleep(1000);
    }
}
