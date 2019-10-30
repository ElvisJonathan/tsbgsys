package com.tsbg.mis.doorMapper;

import com.tsbg.mis.doorVo.GetMsgVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GetMsgMapper {

    //通过userCode查询用户所有信息
    GetMsgVo selectMsgByUserCode(String userCode);
}
