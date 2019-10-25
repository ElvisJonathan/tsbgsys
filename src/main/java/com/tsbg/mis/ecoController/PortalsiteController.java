package com.tsbg.mis.ecoController;

import com.tsbg.mis.annotation.UserLoginToken;
import com.tsbg.mis.ecoModel.FriendlyLink;
import com.tsbg.mis.ecoService.FriendlyLinkService;
import com.tsbg.mis.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tsbg/portalsite")
public class PortalsiteController {

    @Autowired
    private FriendlyLinkService friendlyLinkService;

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
}
