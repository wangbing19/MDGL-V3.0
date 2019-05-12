package com.vision.service.ppo;

import java.util.List;

import com.vision.pojo.ppo.PpoAppointment;
import com.vision.vo.PageObject;

public interface PpoAppointmentservice {

	PageObject findAll(String appointmentName, Integer pageCurrent);

	int saveppoAppointment(PpoAppointment ppoAppointment);

	int updateAppointment(PpoAppointment ppoAppointment);

	Integer dodeleteAppointment(Integer ppoAppointmentId, Long userId);

	

	

}
