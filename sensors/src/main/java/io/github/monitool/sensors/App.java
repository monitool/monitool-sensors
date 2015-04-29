package io.github.monitool.sensors;

import java.io.IOException;
import java.net.ServerSocket;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.varia.NullAppender;

public class App {
	private static ServerSocket socket;

	public static void main(String[] args) {
		oneInstanceAplication();
		runAplication();

	}

	private static void runAplication() {
		org.apache.log4j.BasicConfigurator.configure(new NullAppender());
		Logger.getRootLogger().setLevel(Level.ERROR);
		SensorConfiguration.start();
		MeasurementScheduler.startMeasurements(SensorConfiguration
				.getInstance().getSendingCronExp());
	}

	private static void oneInstanceAplication() {
		new Thread() {
			@Override
			public void run() {
				try {
					socket = new ServerSocket(12999);
					while (true)
						Thread.sleep(1000000000L);

				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.exit(-1);
				}
			}

		}.start();
		while (socket == null || !socket.isBound())
			try {
				Thread.sleep(10L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
