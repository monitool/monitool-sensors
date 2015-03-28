package io.github.monitool.sensors;

import java.lang.management.ManagementFactory;

import com.sun.management.OperatingSystemMXBean;

public class Monitor {

	OperatingSystemMXBean mxBean;

	public Monitor() {
		mxBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
	}
	
	public Measure getMesure() {
		Measure measure = new Measure();
		measure.setCpuLoad(getCpuUsage());
		measure.setMemLoad(getMemoryUsage());
		return measure;
	}
	
	private double getCpuUsage() {
		return mxBean.getSystemCpuLoad();
	}
	
	private double getMemoryUsage() {		
		double free = mxBean.getFreePhysicalMemorySize() / 1024;
		double total = mxBean.getTotalPhysicalMemorySize() / 1024;
		double usage = (total - free) / total;
		return usage;
	}
}
