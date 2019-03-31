package com.vision.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value="ppo_appointment")
public class Appointment extends BasePojo{
	@TableId(type=IdType.AUTO)
	private Long id;
	private String trainerName;
	private String gender;
	private String tel;
	private String post;
	private String description;
	private String resume;
	private Long userId;
	private String userName;
	
	

}
