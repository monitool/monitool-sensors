package io.github.monitool.sensors;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class SensorConfiguration {
	private static SensorConfiguration configuration;

	private String serverAddres;
	private String sensorId;

	private SensorConfiguration() {
		PropertiesConfiguration config = null;
		try {
			config = new PropertiesConfiguration("application.properties");
			serverAddres = config.getString("server.ip", null);
			sensorId = config.getString("sensor.id", null);
			if (sensorId == null) {

				config.setProperty("sensor.id", sensorId);
				config.save();
			}

		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String registerSensor(String serverAddress) {
		ClientHttp clientHttp = new ClientHttp();
		// clientHttp.get(serverAddress);?
		return null;
	}

	static SensorConfiguration getInstance() {
		if (configuration == null)
			configuration = new SensorConfiguration();
		return configuration;
	}

	static public String getServerAddress() {
		return getInstance().serverAddres;
	}

	static public String getSensorId() {
		return getInstance().sensorId;
	}
}
