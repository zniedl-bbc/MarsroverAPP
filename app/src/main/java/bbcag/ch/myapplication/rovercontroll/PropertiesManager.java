package bbcag.ch.myapplication.rovercontroll;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {
	Properties properties = new Properties();

	public PropertiesManager(){
		properties.setProperty("ip", "192.168.1.200");
		properties.setProperty("port", "8081");
		properties.setProperty("user", "pi");
	}
	public String getIp() throws IOException {
		return properties.getProperty("ip");
	}

	public String getPort() throws IOException {
		return properties.getProperty("port");
	}

	public String getUser() throws IOException {
		return properties.getProperty("user");
	}

}