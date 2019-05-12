package com.vision.service.exp;

import com.vision.pojo.exp.RemoteDiagnose;
import com.vision.pojo.exp.vo.ExpRemoteDiagnoseVo;
import com.vision.vo.PageObject;

public interface RemoteDiagnoseService {

    PageObject<ExpRemoteDiagnoseVo> findPageObjects(String customerName, Integer pageCurrent,Integer parentId);

	ExpRemoteDiagnoseVo select(Integer id);

	Integer validById(Integer id,Integer valid,String modifyuser);

	Integer delete(Integer[] ids);

	Integer saveObject(RemoteDiagnose entity);

	Integer update(RemoteDiagnose entity);
}
