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
			measure.setSensorId(SensorConfiguration.getInstance().getSensorId());
			Response response = clientHttp.post(SensorConfiguration
					.getInstance().getServerAddress() + "api/data",
					measure.toJson());
			if (!response.isSuccessful()) {
				SensorConfiguration.getInstance().registerSensor();
				clientHttp.post(SensorConfiguration.getInstance()
						.getServerAddress() + "api/data", measure.toJson());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
