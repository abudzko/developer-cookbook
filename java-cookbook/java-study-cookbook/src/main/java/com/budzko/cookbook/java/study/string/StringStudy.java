package com.budzko.cookbook.java.study.string;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringStudy {

    public static void main(String[] args) throws IOException {
//        inefficientConcatenationInLoop();
//        printFormattedStringInLoop();
        patternAppendReplacement();
        System.out.println(System.class.getSimpleName());

        var a = "";

        HashSet<String> strings = new HashSet<>();
//        strings.add("2");
//        strings.add("1");
//        strings.add("0");
//        strings.forEach(new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        });
    }

    private static void patternAppendReplacement() {
        Pattern pattern = Pattern.compile("\\d+");
        String source = "abc123x abc45y abc99z\nabc321z abc54y abc11x";

        Matcher m = pattern.matcher(source);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "TTT");
        }
        m.appendTail(sb);
        System.out.println("Source:");
        System.out.println(source);
        System.out.println("Output:");
        System.out.println(sb.toString());
    }

    private static void printFormattedStringInLoop() throws IOException {
        Iterable<String> nextWordProducer = getNextWordProducer();
        Iterator<String> iterator = nextWordProducer.iterator();
        Formatter formatter = new Formatter();
        int columnCount = 5;
        String format = String.format("%%%s.%ss", 10, 8);

        while (iterator.hasNext()) {
            for (int j = 0; j < columnCount; j++) {
                formatter.out().append("|");
                String value = "";
                if (iterator.hasNext()) {
                    value = iterator.next();
                }
                formatter.format(format, value);
            }
            formatter.out().append("|\n");
        }
        System.out.println(formatter.toString());
    }

    private static Iterable<String> getNextWordProducer() throws IOException {
        class NextWordProducer implements Iterable<String> {
            private final String[] wordsArray;

            public NextWordProducer() throws IOException {
                byte[] bytes = Files.readAllBytes(Paths.get("pom.xml"));
                String str = new String(bytes, StandardCharsets.UTF_8);
                this.wordsArray = str.split("\\s+");
            }

            @Override
            public Iterator<String> iterator() {

                return new Iterator<String>() {
                    List<String> words = Arrays.asList(wordsArray);
                    int i = 0;

                    @Override
                    public boolean hasNext() {
                        return i < words.size();
                    }

                    @Override
                    public String next() {
                        return words.get(i++);
                    }
                };
            }
        }
        return new NextWordProducer();
    }

    private static void inefficientConcatenationInLoop() {
        String s = "";
        for (int i = 0; i < 500; i++) {
            s += i;//InvokeDynamic #0:makeConcatWithConstants:(Ljava/lang/String;I)Ljava/lang/String;
            //Also see bytecode (javap -c StringApiClass.class)
            //More robustly is to use StringBuilder(StringBuffer - is thread-safe, can affect performance)
        }
        System.out.println(s);
    }
}
