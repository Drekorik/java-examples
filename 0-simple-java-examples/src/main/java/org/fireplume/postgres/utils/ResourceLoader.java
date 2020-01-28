package org.fireplume.postgres.utils;

import java.io.InputStream;

/**
 * Util class for reading files from resources
 */
public final class ResourceLoader {
    private ResourceLoader() {
    }

    public static InputStream getResource(final String file) {
        return ResourceLoader.class.getResourceAsStream(String.format("/%s", file));
    }
}
