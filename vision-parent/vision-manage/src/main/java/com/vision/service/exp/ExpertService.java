package com.vision.service.exp;

import com.vision.pojo.exp.Expert;
import com.vision.vo.Node;
import com.vision.vo.PageObject;

public interface ExpertService {

	Node[] selectExpName();

	PageObject<Expert> limitExp(String expertName, Integer pageCurrent);

	Expert selectExp(Integer id);

	Integer updateExp(Expert entity);

	Integer deleteExp(Integer[] ids);

	Integer saveObject(Expert entity);

	Integer updateMessage(Expert entity);

}
