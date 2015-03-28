package io.github.monitool.sensors;

import java.lang.management.ManagementFactory;

import com.jezhumble.javasysmon.JavaSysMon;
import com.sun.management.OperatingSystemMXBean;

public class Monitor {

	private JavaSysMon sysMon;
	OperatingSystemMXBean mxBean;

	public Monitor() {
		sysMon = new JavaSysMon();
		mxBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
	}
	
	public Measure getMesure() {
		Measure measure = new Measure();
		getCpuUsage();
		getMemoryUsage();
		return measure;
	}
	
	private double getCpuUsage() {
		System.out.println("sysMon cpu usage: " + sysMon.cpuTimes().getCpuUsage(sysMon.cpuTimes()));
		System.out.println("mxBean cpu load: " + mxBean.getSystemCpuLoad());
		
		return -1.0;
	}
	
	private double getMemoryUsage() {
		System.out.println("sysMon free memory: " + sysMon.physical().getFreeBytes() / 1024);
		System.out.println("sysMon used memory: " + sysMon.physical().getTotalBytes() / 1024);
		System.out.println("mxBean free memory: " + mxBean.getFreePhysicalMemorySize() / 1024);
		System.out.println("mxBean used memory: " + mxBean.getTotalPhysicalMemorySize() / 1024);
		
		return -1.0;
	}
}
