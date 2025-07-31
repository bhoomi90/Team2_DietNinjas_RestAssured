package utilities;

import java.io.FileInputStream;
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

}
