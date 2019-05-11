package com.vision.service.cus;

import com.vision.pojo.cus.CusCustomer;
import com.vision.pojo.cus.vo.CusVo;
import com.vision.pojo.rec.RecPayUser;
import com.vision.pojo.tra.TraInformationrecord;
import com.vision.vo.PageObject;

public interface CusCustomerService {

	/**用户页面查看所有信息*/
	PageObject<CusCustomer> findPageObjects(CusVo cusVo);
	/**基于客户id查询客户所有信息*/
	CusCustomer findObjectById(Integer id);
	/**基于用户id修改用户状态*/
	Integer updateStateById(CusVo cusVo);
	/**根据咨询表id查询客户表信息有无*/
	Integer findConsultationByConsultationId(Integer consultationId);
	/**将CusCustomer类型数据添加到数据库*/
	Integer saveObject(CusCustomer cusCustomer);
	/**基于id删除客户信息*/
	Integer deleteObject(Integer id);
	/**基于客户id修改客户信息*/
	Integer updateObject(CusCustomer cusCustomer);
	/**基于用户id修改金额,余额及充值次数*/
	Integer updateObjectByMoney(RecPayUser recPayUser);
	/**基于训练记录表返回信息更改训练次数*/
	Integer updateObjectByTimesOfTraining(TraInformationrecord entity);
}
