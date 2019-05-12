package com.vision.service.exp;

import com.vision.pojo.exp.SymptomsDescribed;

public interface SymptomsDescribedService {

	SymptomsDescribed selectSym(Integer id);

	Integer insertSym(SymptomsDescribed entity);

	Integer updateSym(SymptomsDescribed entity);
	
	Integer deleteSym(Integer[] id);

}
