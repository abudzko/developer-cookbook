package com.budzko.cookbook.java.study.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class NioStudy {
    public static void main(String[] args) throws Exception {
        fileChannelFromFileInputStream();
        charBuffer();
        byteBuffer();
    }

    private static void byteBuffer() {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        for (int i = 0; i < buffer.capacity() - 1; i++) {
            buffer.put((byte) i);
        }
        buffer.flip();
        System.out.println(buffer.position());
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());
        System.out.println(buffer.remaining());
    }

    private static void fileChannelFromFileInputStream() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("pom.xml"));
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(256);
        fileChannel.read(byteBuffer);
        System.out.println(byteBuffer.remaining());
        byteBuffer.flip();
        System.out.println(byteBuffer.remaining());
        System.out.println(new String(byteBuffer.array(), StandardCharsets.UTF_8));
    }

    private static void charBuffer() throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File("pom.xml"));
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(256);
        fileChannel.read(byteBuffer);

        byteBuffer.flip();
        CharBuffer charBuffer = byteBuffer.asCharBuffer();//utf-16 is used to convert bytes to chars
        System.out.println(charBuffer);
        String encoding = System.getProperty("file.encoding");
        System.out.println(encoding);
        System.out.println(Charset.defaultCharset());
        System.out.println(new String(byteBuffer.array(), StandardCharsets.UTF_16));//the same result as  byteBuffer.asCharBuffer()
        CharBuffer utf8DecodedCharBuffer = StandardCharsets.UTF_8.decode(byteBuffer);
        System.out.println(utf8DecodedCharBuffer);
        System.out.println(new String(byteBuffer.array(), StandardCharsets.UTF_8));

        String s = new String(byteBuffer.array(), StandardCharsets.UTF_8);
        byte[] utf16Bytes = s.getBytes(StandardCharsets.UTF_16);
        ByteBuffer utf16ByteBuffer = ByteBuffer.wrap(utf16Bytes);
        System.out.println(utf16ByteBuffer.asCharBuffer());
    }
}
