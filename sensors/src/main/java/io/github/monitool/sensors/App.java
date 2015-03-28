package io.github.monitool.sensors;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.varia.NullAppender;

public class App {

	public static void main(String[] args) {
		org.apache.log4j.BasicConfigurator.configure(new NullAppender());
		Logger.getRootLogger().setLevel(Level.ERROR);
		SensorConfiguration.start();
		MeasurementScheduler.startMeasurements(SensorConfiguration
				.getInstance().getSendingCronExp());
	}
}
