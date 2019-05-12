package com.vision.pojo.ppo;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.vision.pojo.BasePojo;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
@TableName("ppo_appintmenttime")
public class PpoAppointmentTime extends BasePojo{
	   @TableId(type = IdType.AUTO)
		private Long id; //id
		private Integer appointmentId; //训练师id
		private Date startTime; // 训练师预约开始时间
		private Date endTime; // 训练师预约结束时间
		
}
