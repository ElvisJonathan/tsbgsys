package com.tsbg.mis.doorService;

import com.alibaba.fastjson.JSONObject;
import com.tsbg.mis.doorMapper.GetMsgMapper;
import com.tsbg.mis.doorVo.GetMsgVo;
import com.tsbg.mis.ecoService.base.RedisService;
import com.tsbg.mis.util.ResultUtils;
import com.tsbg.mis.util.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetMsgServiceImpl implements GetMsgService {

    @Autowired
    private GetMsgMapper getMsgMapper;
    @Autowired
    private RedisService redisService;

    //通过工号查询用户信息
    @Override
    public ResultUtils GetMsg(/*String userCode*/) {
        JSONObject userInfo = redisService.getCacheObject(Constants.SESSION_USER_INFO);
        String userCode = userInfo.getString("userCode");
        System.out.println("取得的工号："+userCode);
        GetMsgVo getMsgVo = getMsgMapper.selectMsgByUserCode(userCode);
        if (getMsgVo!=null){
            return new ResultUtils(ResultUtils.getSUCCESS(),"成功查询",getMsgVo);
        }
        return new ResultUtils(ResultUtils.getFAILED(),"未能查到用户信息");
    }
}
