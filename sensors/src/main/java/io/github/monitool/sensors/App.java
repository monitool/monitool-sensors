package io.github.monitool.sensors;

public class App {

	public static void main(String[] args) {
		SensorConfiguration.start();
		MeasurementScheduler.startMeasurements(SensorConfiguration
				.getInstance().getSendingCronExp());
	}
}
