package io.github.monitool.sensors;

import io.github.monitool.sensors.jsonModels.Measure;

import java.io.File;
import java.lang.management.ManagementFactory;

import javax.swing.filechooser.FileSystemView;

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
		measure.setDiscLoad(getDiscUsage());
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
	
	
	private double getDiscUsage(){
	   
		FileSystemView fsv = FileSystemView.getFileSystemView();
        File[] f = File.listRoots();
        int noOfPartitions = 0;
        double usage = 0.0;
        for (int i = 0; i < f.length; i++) {
        	if(fsv.isDrive(f[i]) && f[i].canRead() &&  f[i].canWrite()){ //if is HDD
        		++noOfPartitions;
        		usage += (f[i].getTotalSpace() - f[i].getUsableSpace())/(double)f[i].getTotalSpace();
        	}
        }
        return noOfPartitions == 0 ? 1.0 : usage/noOfPartitions;
	}
	
}
