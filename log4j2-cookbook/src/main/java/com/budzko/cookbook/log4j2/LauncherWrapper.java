package com.budzko.cookbook.log4j2;

import java.io.IOException;
import java.io.InputStream;

public class LauncherWrapper {
    private java.util.logging.Logger julLogger = java.util.logging.Logger.getLogger(getClass().getName());

    public static void main(String[] args) throws IOException {
        new LauncherWrapper().start();
    }

    private void start() throws IOException {
        julLogger.info("LauncherWrapper:JUL:start");
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("java",
                "-Djava.util.logging.manager=java.util.logging.LogManager",
                "-Djava.util.logging.manager=org.apache.logging.log4j.jul.LogManager",
                "-cp", "D:/work/projects/developer-cookbook/log4j2-cookbook/target/classes;D:/work/projects/developer-cookbook/log4j2-cookbook/target/classes;C:/Users/User/.m2/repository/org/apache/logging/log4j/log4j-api/2.14.0/log4j-api-2.14.0.jar;C:/Users/User/.m2/repository/org/apache/logging/log4j/log4j-core/2.14.0/log4j-core-2.14.0.jar;C:/Users/User/.m2/repository/org/apache/logging/log4j/log4j-jul/2.14.0/log4j-jul-2.14.0.jar\"",
                " com.budzko.cookbook.log4j2.SwingAppJFrame"
        );
        Process start = processBuilder.start();
        InputStream inputStream = start.getInputStream();
//        InputStream inputStream = start.getErrorStream();
        int ch;
        while ((ch = inputStream.read()) != -1) {
            System.out.print((char) ch);
        }
    }
}
