package io.github.monitool.sensors;

import com.google.gson.Gson;

public class Measure {
	
	private String sensorId;
	private double cpuLoad;
	private double memLoad;
	
	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public double getCpuLoad() {
		return cpuLoad;
	}

	public void setCpuLoad(double cpuLoad) {
		this.cpuLoad = cpuLoad;
	}

	public double getMemLoad() {
		return memLoad;
	}

	public void setMemLoad(double memLoad) {
		this.memLoad = memLoad;
	}

	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	@Override
	public String toString() {
		return "CPU load: " + cpuLoad*100 + "%\n" +
				"Memory usage: " + memLoad*100 + "%\n";
	}
}
