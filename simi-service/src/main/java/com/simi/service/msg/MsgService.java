package com.simi.service.msg;

import java.util.List;

import com.simi.po.model.msg.Msg;
import com.simi.vo.MsgSearchVo;
import com.simi.vo.msg.MsgVo;

public interface MsgService {
	
	int deleteByPrimaryKey(Long msgId);

    int insert(Msg record);

    int insertSelective(Msg record);

    Msg selectByPrimaryKey(Long msgId);

    int updateByPrimaryKeySelective(Msg record);

    int updateByPrimaryKey(Msg record);

    Msg initMsg();
    
	MsgVo getMsgList(Msg msg);

	List<Msg> selectMsgListBySearchVo(MsgSearchVo searchVo, int pageNo,
			int pageSize);


}