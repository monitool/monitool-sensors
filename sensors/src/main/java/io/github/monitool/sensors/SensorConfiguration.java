package io.github.monitool.sensors;

import io.github.monitool.sensors.jsonModels.HostName;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.json.JSONObject;

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
				sensorId = registerSensor(serverAddres);
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
		HostName name = new HostName(createName());
		String sensorId = null;
		try {
			String response = clientHttp.post(serverAddress + "api/sensors",
					name.toJson());
			System.out.println(response);
			JSONObject obj = new JSONObject(response);
			sensorId = obj.getString("id");
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
