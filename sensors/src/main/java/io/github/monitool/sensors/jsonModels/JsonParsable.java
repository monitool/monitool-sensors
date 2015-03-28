package io.github.monitool.sensors.jsonModels;

import com.google.gson.Gson;

public class JsonParsable {
	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
