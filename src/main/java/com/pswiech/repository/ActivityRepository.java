package com.pswiech.repository;

import java.util.List;

import com.pswiech.model.Activity;

public interface ActivityRepository {

	List<Activity> findAllActivities();

	Activity findActivity(String activityId);

	void create(Activity activity);

}