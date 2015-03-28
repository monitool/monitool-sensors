package io.github.monitool.sensors;

import com.google.gson.Gson;

public class Measure {
	private double cpu;
	private double memory;
	
	

	public double getCpu() {
		return cpu;
	}



	public void setCpu(double cpu) {
		this.cpu = cpu;
	}



	public double getMemory() {
		return memory;
	}



	public void setMemory(double memory) {
		this.memory = memory;
	}



	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
