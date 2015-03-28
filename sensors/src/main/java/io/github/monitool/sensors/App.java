package io.github.monitool.sensors;

//import it.sauronsoftware.cron4j.Scheduler;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		SensorConfiguration.start();
		MeasurementScheduler.startMeasurements("0/1 * * * * ?");

		System.out.println("Hello World!");

	}
}
