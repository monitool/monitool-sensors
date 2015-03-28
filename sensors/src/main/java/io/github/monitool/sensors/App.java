package io.github.monitool.sensors;

//import it.sauronsoftware.cron4j.Scheduler;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		// MeasurementScheduler.startMeasurements("0/1 * * * * ?");

		System.out.println(SensorConfiguration.getSensorId());
		System.out.println("Hello World!");

	}
}
