package com.pswiech.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.pswiech.model.Activity;

public class ActivityClient {

	private Client client;

	public ActivityClient() {
		client = ClientBuilder.newClient();
	}

	public Activity get(String id) {
		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");

		// for debug: change type and class to String and return to null
		Response response = target.path("activities/" + id).request(MediaType.APPLICATION_JSON).get(Response.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + ": there was an error on the server");
		}

		System.out.println(response);

		return response.readEntity(Activity.class);
	}

	public List<Activity> getList() {
		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");

		List<Activity> response = target.path("activities").request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Activity>>() {
				});

		return response;
	}

	public Activity create(Activity activity) {
		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");

		Response response = target.path("activities/activity").request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(activity, MediaType.APPLICATION_JSON));

		if (response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + ": there was an error on the server");
		}

		return response.readEntity(Activity.class);
	}

}
