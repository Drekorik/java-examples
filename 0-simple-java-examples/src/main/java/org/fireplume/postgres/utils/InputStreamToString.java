package org.fireplume.postgres.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Util class for reading {@link InputStream} into String
 */
public final class InputStreamToString {
    private static final int READ_BYTES = 1024;

    private InputStreamToString() {
    }

    public static String read(final InputStream inputStream) {
        try (ByteArrayOutputStream result = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[READ_BYTES];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            return result.toString("UTF-8");
        } catch (IOException e) {
            return "";
        }
    }
}
