package com.vision.pojo.exp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 对应症状描述表
 * @author tarena
 *
 */
@Data
@Accessors(chain=true)
@TableName("exp_symptoms_described")
public class SymptomsDescribed {
    /**症状描述表序号*/
	@TableId(type=IdType.AUTO)
    private Integer id;
    /**远程诊断表序号*/
    private Integer remoteDiagnoseId;
    /**症状描述*/
    private String symptomsDescribed;
    /**症状备注*/
    private String symptomsRemark;
    /**创建时间*/
    private Date gmtCreate;
    /**修改时间*/
    private Date gmtModified;
    /**门店id*/
    private Integer userId;
    /**上级门店id*/
    private Integer userParentId;
}
