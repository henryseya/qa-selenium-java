package com.qaengineer.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static final Properties properties = new Properties();
    private static ConfigManager instance;

    // Singleton — una sola instancia para toda la suite
    private ConfigManager() {
        try {
            InputStream input = getClass()
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            if (input == null) {
                throw new RuntimeException("config.properties no encontrado");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error cargando config.properties: " + e.getMessage());
        }
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    public String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Propiedad no encontrada: " + key);
        }
        return value;
    }

    public String getBaseUrl() {
        return get("base.url");
    }

    public int getTimeout() {
        return Integer.parseInt(get("timeout.seconds"));
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(get("browser.headless"));
    }
}