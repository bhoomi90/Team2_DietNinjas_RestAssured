package utilities;

import java.io.FileInputStream;
<<<<<<< HEAD
import java.io.IOException;
import java.util.Properties;
=======
import java.util.Properties;

public class configReader {
	
	static Properties prop = new Properties();
	public static Properties getProperties() {
		
		try {
		
		String projectpath = System.getProperty("user.dir");
		FileInputStream file = new FileInputStream(projectpath+ "\\src\\test\\resources\\config.properties");
		prop.load(file);
		}
		catch(Exception ex) {
			
			ex.printStackTrace();
		}
		
		return prop;
	}
	
	public static String getProperty(String key) {
		getProperties();
        if (prop == null) {
            throw new RuntimeException("Properties file not initialized!");
        }
        return prop.getProperty(key);
    }
>>>>>>> origin/Sumathi

public class configReader {
    
    private static final Properties properties = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties file", e);
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in config.properties");
        }
        return value;
    }
}
