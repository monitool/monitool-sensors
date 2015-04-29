package io.github.monitool.sensors;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.varia.NullAppender;

public class App {

	public static void main(String[] args) {
		// try {
		// ServerSocket socket = new ServerSocket(14999);
		// Thread.sleep(1000);
		// ServerSocket socket2 = new ServerSocket(14998);
		runAplication();
		//
		// } catch (java.net.BindException b) {
		// System.out.println("Already Running...");
		// } catch (Exception e) {
		// System.out.println(e.toString());
		// }
	}

	private static void runAplication() {
		org.apache.log4j.BasicConfigurator.configure(new NullAppender());
		Logger.getRootLogger().setLevel(Level.ERROR);
		SensorConfiguration.start();
		MeasurementScheduler.startMeasurements(SensorConfiguration
				.getInstance().getSendingCronExp());
	}
}
