package com.vision.service.sys;



import java.util.List;
import java.util.Map;

import com.vision.pojo.sys.Depts;
import com.vision.vo.Node;



public interface DeptService {
	int saveObject(Depts entity);

	int updateObject(Depts entity);

	List<Node> findZTreeNodes();

	List<Map<String, Object>> findObjects();

	int deleteObject(Integer id);
}
