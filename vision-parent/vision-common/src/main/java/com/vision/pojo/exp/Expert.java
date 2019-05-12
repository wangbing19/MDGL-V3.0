package com.vision.pojo.exp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 对应专家表
 * @author tarena
 *
 */
@Data
@Accessors(chain=true)
@TableName("exp_expert")
public class Expert {
    /**专家表序号*/
	@TableId(type=IdType.AUTO)
    private Integer id;
    /**专家姓名*/
    private String expertName;
    /**专家电话*/
    private String expertTel;
    /**专家信息*/
    private String expertMessage;
    /**预约时间*/
    private String appointmentTime;
    /**门店id*/
    private Integer userId;
    /**上级门店id*/
    private Integer userParentId;
}
