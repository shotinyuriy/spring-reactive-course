package log.util;

import java.nio.charset.StandardCharsets;

public class LogUtil {
    public static String encode(String str) {
        return new String(str.getBytes(StandardCharsets.UTF_8));
    }
}
