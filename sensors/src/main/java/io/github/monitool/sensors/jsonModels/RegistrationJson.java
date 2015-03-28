package io.github.monitool.sensors.jsonModels;

import com.google.gson.Gson;

public class RegistrationJson {
	private String name;
	private String dateJoined;
	private String id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateJoined() {
		return dateJoined;
	}

	public void setDateJoined(String dateJoined) {
		this.dateJoined = dateJoined;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static RegistrationJson fromJson(String json) {
		Gson gson = new Gson();
		RegistrationJson obj = gson.fromJson(json, RegistrationJson.class);
		return obj;

	}
}
