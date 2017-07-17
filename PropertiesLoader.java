package com.cummins.cs.hsdi.poc.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Reusing Peng Ren's Class
 * Load configuration from properties file. All the properties will be loaded once to memory.
 * If properties are changed in the file, the application has to reboot to load the changes.
 */
public class PropertiesLoader {

	private PropertiesLoader() {}
	
	private static Map<String, Properties> propertiesMap = new HashMap<String, Properties>();
		
	public static Properties getProperties(String fileName) throws IOException {
		if (!propertiesMap.containsKey(fileName)) {
			loadProperties(fileName);
		}
		return propertiesMap.get(fileName);
	}
	
	private static void loadProperties(String fileName) throws IOException {
		Properties properties = new Properties();
		InputStream isProperties = PropertiesLoader.class.getResourceAsStream(fileName);
		properties.load(isProperties);
		propertiesMap.put(fileName, properties);
	}
}
