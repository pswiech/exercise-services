package com.pswiech.view;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.pswiech.model.Activity;
import com.pswiech.model.User;
import com.pswiech.repository.ActivityRepository;
import com.pswiech.repository.ActivityRepositoryStub;

@Path("activities")
public class ActivityResource {

	// use Spring to inject this
	private ActivityRepository activityRepository = new ActivityRepositoryStub();

	@POST
	@Path("activity")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Activity createActivity(Activity activity) {
		
		System.out.println("POST activity: " + activity.getDescription() + ", " + activity.getDuration());
		activityRepository.create(activity);

		return activity;
	}
	
	@POST
	@Path("activity")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Activity createActivityParams(MultivaluedMap<String, String> formParams){
		
		System.out.println(formParams.getFirst("description"));
		System.out.println(formParams.getFirst("duration"));
		
		Activity activity = new Activity();
		activity.setDescription(formParams.getFirst("description"));
		//activity.setDuration(Integer.valueOf(formParams.getFirst("duration")));
		activity.setDuration(Integer.parseInt(formParams.getFirst("duration")));
		
		activityRepository.create(activity);
		
		return activity;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Activity> findAllActivities() {
		return activityRepository.findAllActivities();
	}

	@GET
	@Path("{activityId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getActivity(@PathParam("activityId") String activityId) {

		Response response = null;
		
		if (activityId == null || activityId.length() < 4) {
			response = Response.status(Status.BAD_REQUEST).build();
		}
		else {
			
			Activity activity = activityRepository.findActivity(activityId);
			response = Response.status(Status.OK).build();
			
			if (activity == null) {
				response = Response.status(Status.NOT_FOUND).build();
			}
			else {
				//response = Response.status(Status.OK).entity(activity).build();				
				response = Response.ok().entity(activity).build();				
			}
		}
		System.out.println("getActivity is called (to debug - remote app needed)");
		return response;
	}

	@GET
	@Path("{activityId}/user")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public User getUser(@PathParam("activityId") String activityId) {
		return activityRepository.findActivity(activityId).getUser();
	}
}
