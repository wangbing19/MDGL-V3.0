package com.vision.pojo.ppo;





import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.vision.pojo.BasePojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
@Data
@Accessors(chain = true)
@TableName("ppo_appointment")
public class PpoAppointment extends BasePojo{
    @TableId(type = IdType.AUTO)
    /**
     * 主键ID
     */ 
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
