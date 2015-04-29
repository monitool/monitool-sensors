package io.github.monitool.sensors;

import io.github.monitool.sensors.jsonModels.Measure;

import java.io.IOException;

import com.squareup.okhttp.Response;

public class MeasurementSender {
	static MeasurementSender instance = null;

	public static MeasurementSender getInstance() {
		if (instance == null)
			instance = new MeasurementSender();
		return instance;
	}

	private ClientHttp clientHttp;

	private MeasurementSender() {
		clientHttp = new ClientHttp();
	}

	synchronized void sendMeasurement(Measure measure) {
		try {
			String url = SensorConfiguration.getInstance().getServerAddress()
					+ "api/hosts/"
					+ SensorConfiguration.getInstance().getSensorId()
					+ "/data/";
			String msgBody = "{\"instance\":" + measure.toJson() + "}";
			Response response = clientHttp.post(url, msgBody);
			if (!response.isSuccessful()) {
				SensorConfiguration.getInstance().registerSensor();
				clientHttp.post(url, msgBody);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
