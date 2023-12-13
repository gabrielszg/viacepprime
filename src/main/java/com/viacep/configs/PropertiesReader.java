package com.viacep.configs;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	
	public static Properties getProperties() {
		InputStream inputStream = getInputStream(); 
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return properties;
	}
	
	private static InputStream getInputStream() {
		InputStream inputStream = PropertiesReader
				.class
				.getClassLoader()
				.getResourceAsStream("application.properties");
		
		return inputStream;
	}
}
