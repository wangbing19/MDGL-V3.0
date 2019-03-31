package com.vision.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value="ppo_appointment")
/**
 * 封装医师表数据
 * @author Administrator
 *
 */
public class Appointment extends BasePojo{
	@TableId(type=IdType.AUTO)
	private Long id; 
	private String trainerName; //训练师姓名
	private String gender; //性别
	private String tel; //电话
	private String post; //职位
	private String description; //描述
	private String resume; //简历
	private Long shopId; //子门店id
	private String shopParentId; //父门店id

	
	

}
