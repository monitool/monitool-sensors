package io.github.monitool.sensors;

import io.github.monitool.sensors.jsonModels.HostName;
import io.github.monitool.sensors.jsonModels.RegistrationJson;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class SensorConfiguration {
	private static SensorConfiguration configuration;

	private String serverAddres;
	private String sensorId;
	private PropertiesConfiguration config;

	private SensorConfiguration() {
		config = null;
		try {
			config = new PropertiesConfiguration("application.properties");
			serverAddres = config.getString("server.ip", null);
			sensorId = config.getString("sensor.id", null);
			if (sensorId == null) {
				registerSensor();
			}

		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	synchronized public static void start() {
		if (configuration == null)
			configuration = new SensorConfiguration();
	}

	synchronized public static String registerSensor(String serverAddress) {
		ClientHttp clientHttp = new ClientHttp();
		HostName name = new HostName(createName());
		String sensorId = null;
		try {
			String response = clientHttp
					.post(serverAddress + "api/sensors", name.toJson()).body()
					.string();
			RegistrationJson regJson = RegistrationJson.fromJson(response);

			sensorId = regJson.getId();
			System.out.println(sensorId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sensorId;
	}

	private static String createName() {
		String name = null;
		try {
			name = InetAddress.getLocalHost().getHostAddress() + ","
					+ InetAddress.getLocalHost().getHostName() + ","
					+ System.getProperty("user.name");

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}

	synchronized static SensorConfiguration getInstance() {

		return configuration;
	}

	public String getServerAddress() {
		return serverAddres;
	}

	public String getSensorId() {
		return sensorId;
	}

	public void registerSensor() {
		try {
			sensorId = registerSensor(serverAddres);
			if (sensorId != null && !sensorId.isEmpty())
				config.setProperty("sensor.id", sensorId);
			config.save();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
