package com.vision.mapper.res;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vision.pojo.res.ResProjectConfig;


public interface ResProjectConfigMapper extends BaseMapper<ResProjectConfig>{

	int getRowCount( @Param("projectName") String projectName,@Param("userId")  Long userId);

	List<ResProjectConfig> findResProjectConfigList(@Param("startIndex") int startIndex,@Param("pageSize")  int pageSize);

}
