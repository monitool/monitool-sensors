package io.github.monitool.sensors;

import io.github.monitool.sensors.jsonModels.Measure;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MeasurementJob implements Job {

	private Monitor monitor;

	public MeasurementJob() {
		monitor = new Monitor();
	}

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		Measure measure = monitor.getMesure();
		MeasurementSender.getInstance().sendMeasurement(measure);

	}

}
