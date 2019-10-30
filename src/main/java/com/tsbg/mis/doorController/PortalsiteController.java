package com.tsbg.mis.doorController;

import com.tsbg.mis.annotation.UserLoginToken;
import com.tsbg.mis.doorModel.FriendlyLink;
import com.tsbg.mis.doorService.FriendlyLinkService2;
import com.tsbg.mis.doorService.GetMsgService;
import com.tsbg.mis.powerModel.UserInfo;
import com.tsbg.mis.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tsbg/portalsite")
public class PortalsiteController {

    @Autowired
    private FriendlyLinkService2 friendlyLinkService;

    @Autowired
    private GetMsgService getMsgService;

    /**
     * 返回门户网站链接信息
     */
    @RequestMapping(value = "/getdata", method = { RequestMethod.GET, RequestMethod.POST })
    @UserLoginToken
    @ResponseBody
    public ResultUtils getSiteURL() {
        List<FriendlyLink> friendlyLinks = friendlyLinkService.selectPortalsiteUrl();
        return new ResultUtils(100,"请求成功",friendlyLinks);
    }

    /**
     * 根据工号查询用户具体信息
     */
    @RequestMapping(value = "/getMsg", method = { RequestMethod.GET, RequestMethod.POST })
    @UserLoginToken
    @ResponseBody
    public ResultUtils getUserMsg(/*@RequestBody UserInfo userInfo*/) {
        return getMsgService.GetMsg();
    }
}
