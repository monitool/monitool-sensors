package io.github.monitool.sensors;

import io.github.monitool.sensors.jsonModels.HostName;
import io.github.monitool.sensors.jsonModels.RegistrationJson;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class SensorConfiguration {
	private static SensorConfiguration configuration;

	private String serverAddres;
	private String sensorId;
	private String sendingCronExp;
	private PropertiesConfiguration config;

	private SensorConfiguration() {

		config = null;
		ensureConfigFileExisting();
		try {
			config = new PropertiesConfiguration("application.properties");
			serverAddres = config.getString("server.ip", null);
			if (serverAddres == null) {
				serverAddres = "http://monitool.herokuapp.com/";
				updateConfig("server.ip", serverAddres);
			} else if (!serverAddres.endsWith("/")) {
				serverAddres += "/";
			}
			sendingCronExp = config.getString("sensor.cron.exp", null);
			if (sendingCronExp == null) {
				sendingCronExp = "0/1 * * * * ?";
				updateConfig("sensor.cron.exp", sendingCronExp);
			}
			sensorId = config.getString("sensor.id", null);
			if (sensorId == null) {
				registerSensor();
			}

		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		System.out.println("Sensor started:");
		System.out.println("name: " + createName());
		System.out.println("id: " + sensorId);
	}

	private static void ensureConfigFileExisting() {
		try {
			new File("application.properties").createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void updateConfig(String key, String value) {

		try {
			config.setProperty(key, value);
			config.save();
		} catch (ConfigurationException e) {
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
					.post(serverAddress + "api/hosts", name.toJson()).body()
					.string();
			RegistrationJson regJson = RegistrationJson.fromJson(response);

			sensorId = regJson.getId();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Registered sensor: " + sensorId);
		return sensorId;
	}

	private static String createName() {
		String name = null;
		try {
			name = InetAddress.getLocalHost().getHostAddress() + ","
					+ InetAddress.getLocalHost().getHostName() + ","
					+ System.getProperty("user.name");

		} catch (UnknownHostException e) {
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

	public String getSendingCronExp() {
		return sendingCronExp;
	}

	public void registerSensor() {
		sensorId = registerSensor(serverAddres);
		if (sensorId != null && !sensorId.isEmpty())
			updateConfig("sensor.id", sensorId);
	}
}
