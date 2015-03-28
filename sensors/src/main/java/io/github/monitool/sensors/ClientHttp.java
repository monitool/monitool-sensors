package io.github.monitool.sensors;

import java.io.IOException;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class ClientHttp {
	private OkHttpClient client = new OkHttpClient();
	public static final MediaType JSON = MediaType
			.parse("application/json; charset=utf-8");

	public String get(String url) throws IOException {
		Request request = new Request.Builder().url(url).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}

	public String post(String url, String json) throws IOException {
		System.out.println(url);
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).post(body).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}
}
