package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class consists of methods related to read data from property file
 */
public class PropertyFileUtility {
	/**
	 * This method is used to read the data from property file provided with key
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String propertyFileData(String key) throws IOException {
		FileInputStream file = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
		Properties properties = new Properties();
		properties.load(file);
		String value = properties.getProperty(key);
		return value;
	}
}
