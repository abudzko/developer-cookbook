package com.budzko.cookbook.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

public class SwingAppJFrame extends JFrame {

    private static void setJULManager() {
//        System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
//        System.setProperty("java.util.logging.manager", "java.util.logging.LogManager");
    }

    private Logger logger = LogManager.getLogger(getClass());

    private java.util.logging.Logger julLogger = java.util.logging.Logger.getLogger(getClass().getName());

    public static void main(String[] args) {
        setJULManager();
        new SwingAppJFrame().start();
    }

    private void start() {
        runThreadForJul();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension size = new Dimension(300, 400);
        setMinimumSize(size);
        setPreferredSize(size);
        setLayout(new FlowLayout());
        for (int i = 0; i < 5; i++) {
            JButton jButton = new JButton("Click me " + i);
            add(jButton);
            final int id = i;
            jButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    logger.warn("Log4j: Was pressed button:" + id);
                }
            });
        }
        setVisible(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    logger.error("Log4j:Application still is alive");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    logger.info("Log4j:ping message");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void runThreadForJul() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    julLogger.warning("JUL: ping message");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

