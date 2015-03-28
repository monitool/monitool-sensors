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
		measure.setCpu(getCpuUsage());
		measure.setMemory(getMemoryUsage());
		return measure;
	}
	
	private double getCpuUsage() {
		return mxBean.getSystemCpuLoad();
	}
	
	private double getMemoryUsage() {		
		return mxBean.getFreePhysicalMemorySize() / mxBean.getTotalPhysicalMemorySize();
	}
}
