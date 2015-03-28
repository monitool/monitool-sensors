package io.github.monitool.sensors;

import io.github.monitool.sensors.jsonModels.Measure;

import java.io.IOException;

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

	void sendMeasurement(Measure measure) {
		try {
			measure.setSensorId(SensorConfiguration.getSensorId());
			System.out.println(measure.toJson());
			String response = clientHttp.post(
					SensorConfiguration.getServerAddress() + "api/data",
					measure.toJson());
			System.out.println(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
