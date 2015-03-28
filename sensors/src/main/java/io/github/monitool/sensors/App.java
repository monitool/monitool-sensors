package io.github.monitool.sensors;

import java.io.IOException;

//import it.sauronsoftware.cron4j.Scheduler;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		MeasurementScheduler.startMeasurements("0/5 * * * * ?");
		ClientHttp client = new ClientHttp();
		try {
			System.out.println(client.get("http://unirest.io/java.html"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Hello World!");

	}

}
