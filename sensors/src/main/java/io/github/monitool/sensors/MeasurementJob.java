package io.github.monitool.sensors;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MeasurementJob implements Job {

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println(System.currentTimeMillis() / 1000);
		System.out.println("Hello Quartz!");

	}

}
