package com.vision.service.ppo;

import java.util.List;

import com.vision.pojo.ppo.PpoAppointmentTime;



public interface PpoAppointmentTimeService {

	Integer doInsertAppointment(PpoAppointmentTime ppoAppointmentTime);

	List<PpoAppointmentTime> dofindappointmentTime(Integer ppoAppointmentId);

	

	

}
