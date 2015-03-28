package io.github.monitool.sensors;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ConfigTest {
	public static void main(String[] args) {

		PropertiesConfiguration config = null;
		try {
			config = new PropertiesConfiguration("application.properties");
			String serverIp = config.getString("server.ip", null);
			System.out.println(serverIp);
			Long sensorId = config.getLong("sensor.id", null);
			System.out.println(sensorId);
			Long id = 121145L;
			config.setProperty("sensor.id", id);
			sensorId = config.getLong("sensor.id", null);
			config.save();
			System.out.println(sensorId);
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
