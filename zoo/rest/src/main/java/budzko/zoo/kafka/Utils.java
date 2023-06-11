package budzko.zoo.kafka;

import org.springframework.messaging.MessageHeaders;

import java.nio.charset.StandardCharsets;

public class Utils {

    public static final String HEADER = "HEADER_ZOO";
    public static final byte[] HEADER_VALUE = "ZOO_APP".getBytes(StandardCharsets.UTF_8);

    public static String readHeader(MessageHeaders headers, String headerName) {
        Object header = headers.get(headerName);
        if (header instanceof byte[] headerValue) {
            return new String(headerValue);
        }
        return "";
    }
}
