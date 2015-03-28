package io.github.monitool.sensors.jsonModels;

public class HostName extends JsonParsable {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HostName(String name) {
		super();
		this.name = name;
	}

}
