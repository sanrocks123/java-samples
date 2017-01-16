package com.sanjeev.go.euro.util;

import java.io.IOException;
import java.util.Properties;

import com.sanjeev.go.euro.exceptions.ResourceNotFoundException;

/**
 * @author Sanjeevkumar_Saxena
 *
 */

public enum PropertiesLoader {

    INSTANCE;

    public Properties loadProperties() {
        Properties prop = new Properties();
        try {
            prop.load(PropertiesLoader.class.getResourceAsStream("/config/go-euro-test-config.properties"));
        } catch (IOException e) {
            throw new ResourceNotFoundException("Error loading '/config/go-euro-test-config.properties' file. Please check classpath.");
        }
        return prop;
    }
}
