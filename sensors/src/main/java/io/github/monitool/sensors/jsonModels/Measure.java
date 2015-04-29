package io.github.monitool.sensors.jsonModels;

public class Measure extends JsonParsable {

	private double cpuLoad;
	private double memLoad;
	private double discLoad;

	public double getDiscLoad() {
		return discLoad;
	}

	public void setDiscLoad(double discLoad) {
		this.discLoad = discLoad;
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

	@Override
	public String toString() {
		return "CPU load: " + cpuLoad * 100 + "%\n" + "Memory usage: "
				+ memLoad * 100 + "%\n" + "Disc usage: " + discLoad * 100
				+ "%\n";
	}
}
