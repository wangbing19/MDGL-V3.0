package com.vision.service.res;



import java.util.List;
import java.util.Map;

import com.vision.pojo.res.ResProjectConfig;
import com.vision.vo.PageObject;



public interface ResProjectConfigService {

	PageObject<ResProjectConfig> dofindObjects(Long userId, String projectName, Integer pageCurrent);

	int doSaveObject(ResProjectConfig entity);

	Integer doUpdate(ResProjectConfig entity);

	int doDelete(Integer[] ids, Integer userId);

	Integer doprojectStateById(Integer id, Integer projectState, Integer userId);



	

	

}
