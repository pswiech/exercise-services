package com.pswiech.repository;

import java.util.ArrayList;
import java.util.List;

import com.pswiech.model.Activity;
import com.pswiech.model.User;

public class ActivityRepositoryStub implements ActivityRepository {
	
	private static List<Activity> activities = new ArrayList<>();

	// prepopulate fake DB
	static {
		Activity activity1 = new Activity();
		activity1.setId("1234");
		activity1.setDescription("Swimming");
		activity1.setDuration(55);
		User user = new User();
		user.setId("9999");
		user.setName("Zenon");
		activity1.setUser(user);
		activities.add(activity1);
		
		Activity activity2 = new Activity();
		activity2.setId("2345");
		activity2.setDescription("Cycling");
		activity2.setDuration(120);
		activities.add(activity2);

		Activity activity3 = new Activity();
		activity3.setId("3456");
		activity3.setDescription("Running");
		activity3.setDuration(15);
		activities.add(activity3);
	}
	
	@Override
	public List<Activity> findAllActivities() {
		return activities;
	}

	@Override
	public Activity findActivity(String activityId) {
		Activity act = null;
		
		for (Activity activity : activities) {
			if (activity.getId().equals(activityId)) {
				act=activity;
				break;
			}
		}
		return act;
	}

	@Override
	public void create(Activity activity) {
		activities.add(activity);
	}
}
