package com.vision.mapper.ppo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vision.pojo.ppo.PpoAppointment;


public interface PpoAppointmentMapper extends BaseMapper<PpoAppointment>{
		
	List<PpoAppointment> findAppointmentList(@Param("startIndex") int startIndex,@Param("pageSize") int pageSize);
	
}
