package com.vision.service.rec;

import java.util.List;

import com.vision.pojo.rec.RecActivityPush;


public interface ActivityPushService {

	List<RecActivityPush> findObjects(Integer activityState);

	String deleteObjectById(Integer id);

	String insertRecActivityObject(RecActivityPush recActivityPush);

	String updateRecActivityObject(RecActivityPush recActivityPush);

	RecActivityPush findPageObjectByTitle(String title);


}
