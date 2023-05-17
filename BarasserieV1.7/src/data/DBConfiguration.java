package data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import exception.DataAccessException;

public class DBConfiguration {
    private static final String CONFIG_FILE = "config.properties";

    private static Properties properties;

    public static Properties getInstance() throws DataAccessException{
        if (properties == null) {
            properties = new Properties();
            try {
                loadProperties();
            } catch (IOException e) {
                throw new DataAccessException("Error loading properties file");
            }
        }
        return properties;
    }

    private static void loadProperties() throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE)) {
            properties.load(fileInputStream);
        }
    }

    public String getUrl() {
        return properties.getProperty("url");
    }

    public String getUsername() {
        return properties.getProperty("username");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }
}
