package com.vision.service.rec;

import java.util.List;

import com.vision.pojo.rec.RecActivityPush;
import com.vision.pojo.rec.RecPayUser;
import com.vision.pojo.sys.Users;


public interface RecUserService {

	List<RecPayUser> findObjectByUserIdAndParentId(Users user);

	RecActivityPush doFindActivityObjectByUserPayType(Integer id);

	String insertObjectRecUser(RecPayUser recPayUser);

}
