package com.xsolve.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PropertiesLoader {
    private static Properties properties = new Properties();

    private static Properties getProperties() {
        if (properties.isEmpty()) loadProperties();
        return properties;
    }

    private static void loadProperties() {
        InputStream input = null;
        String dir = System.getProperty("user.dir");
        Path path = Paths.get(dir, "src", "main", "resources", "config.properties");

        try {
            input = new FileInputStream(path.toString());
            properties.load(input);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static String get(String key) {
        return getProperties().getProperty(key);
    }

}
