package com.vision.service.exp;

import com.vision.pojo.exp.ExpertReply;

public interface ExpertReplyService {

	ExpertReply selectRep(Integer id);

	Integer insertRep(ExpertReply entity);

	Integer deleteRep(Integer[] id);

	Integer updateRep(ExpertReply entity);

}
