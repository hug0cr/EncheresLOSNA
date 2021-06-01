/**
 * 
 */
package fr.eni.encheresLOSNA.dal;

import java.io.IOException;
import java.util.Properties;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 1 juin 2021 - 10:23:05
 */
public class Settings {
	private static Properties properties;
	
	static {
		properties = new Properties();
		try {
			properties.load(Settings.class.getResourceAsStream("settings.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperties(String key) {
		return properties.getProperty(key);
	}
}
