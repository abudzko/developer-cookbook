package com.budzko.cookbook.java.study.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.SequenceInputStream;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class IoStudy {

    public static final int SIZE = 100;
    private static final byte[] bytes = createByteArray(SIZE);

    private static byte[] createByteArray(int size) {
        byte[] bs = new byte[size];
        Random random = new Random();
        random.nextBytes(bs);
        return bs;
    }

    public static void main(String[] args) throws Exception {
        inputStream();
    }

    private static void inputStream() throws Exception {
        InputStream inputStream = getInputStream();
        readInputStream(inputStream);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("abc".getBytes(StandardCharsets.UTF_8));
        readInputStream(byteArrayInputStream);

        StringBufferInputStream stringBufferInputStream = new StringBufferInputStream("abc");
        readInputStream(stringBufferInputStream);

        FileInputStream fileInputStream = new FileInputStream(new File("pom.xml"));
        readInputStream(fileInputStream);

        SequenceInputStream sequenceInputStream = new SequenceInputStream(getInputStream(), getInputStream());
        readInputStream(sequenceInputStream);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(out);
        dataOutputStream.writeInt(1);
        dataOutputStream.writeChar('a');
        dataOutputStream.writeInt(123);

        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(out.toByteArray()));
        int i1 = dataInputStream.readInt();
        char ch = dataInputStream.readChar();
        int i2 = dataInputStream.readInt();
        System.out.println("" + i1 + ch + i2);

        InputStreamReader inputStreamReader = new InputStreamReader(getInputStream(), StandardCharsets.UTF_8);
        readReader(inputStreamReader);

        StringReader stringReader = new StringReader("abc");
        readReader(stringReader);

        FileReader fileReader = new FileReader(new File("pom.xml"));
        readReader(fileReader);

        CharArrayReader charArrayReader = new CharArrayReader("abc".toCharArray());
        readReader(charArrayReader);

    }

    private static InputStream getInputStream() {
        return new InputStream() {
            int i = 0;

            @Override

            public int read() {
                if (i >= SIZE) {
                    return -1;
                }
                return bytes[i++];
            }
        };
    }

    private static void readInputStream(InputStream inputStream) throws IOException {
        int b;
        while ((b = inputStream.read()) != -1) {
            System.out.print(b);
        }
        System.out.println();
    }

    private static void readReader(Reader reader) throws IOException {
        int b;
        while ((b = reader.read()) != -1) {
            System.out.print((char) b);
        }
        System.out.println();
    }
}
