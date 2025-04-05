package com.budzko.cookbook.java.fasterxml;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;

import java.io.*;
import java.util.Random;

public class JsonStreamParser {
    public static void main(String[] args) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        generateJson(outputStream, 10);

        InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        InputStream slowInputStream = new InputStream() {
            @Override
            public int read() throws IOException {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int read = inputStream.read();
                System.out.print((char) read);
                return read;
            }
        };

        JsonFactory jfactory = new JsonFactory();
        JsonParser jParser = jfactory.createParser(slowInputStream);

        String s;
        while (jParser.nextToken() != null) {
            String fieldName = jParser.getCurrentName();

            if ("id".equals(fieldName)) {
                jParser.nextToken();
                s = jParser.getText();
                System.out.println(s);
            }
        }
        jParser.close();
    }

    private static void generateJson(OutputStream outputStream, int elementCount) throws IOException {
        JsonFactory jsonFactory = new JsonFactory();
        JsonGenerator jsonGenerator = jsonFactory.createGenerator(outputStream, JsonEncoding.UTF8);

        Random random = new Random();
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("data");
        jsonGenerator.writeStartArray();

        for (int i = 0; i < elementCount; i++) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("id", Integer.toString(random.nextInt()));
            jsonGenerator.writeStringField("value", "v" + random.nextInt());
            jsonGenerator.writeFieldName("array");
            jsonGenerator.writeStartArray();
            for (int j = 0; j < random.nextInt(10); j++) {
                jsonGenerator.writeString(Integer.toString(random.nextInt(20)));
            }
            jsonGenerator.writeEndArray();
            jsonGenerator.writeEndObject();
        }

        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
        jsonGenerator.close();
    }
}
