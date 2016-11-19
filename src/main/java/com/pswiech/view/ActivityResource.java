package com.pswiech.view;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.pswiech.model.Activity;
import com.pswiech.model.User;
import com.pswiech.repository.ActivityRepository;
import com.pswiech.repository.ActivityRepositoryStub;

@Path("activities")
public class ActivityResource {

	// use Spring here
	private ActivityRepository activityRepository = new ActivityRepositoryStub();

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Activity> findAllActivities() {
		return activityRepository.findAllActivities();
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("{activityId}")
	public Activity getActivity(@PathParam("activityId") String activityId) {
		return activityRepository.findActivity(activityId);
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("{activityId}/user")
	public User getUser(@PathParam("activityId") String activityId) {
		return activityRepository.findActivity(activityId).getUser();
	}

}
