package com.tsbg.ecosys.controller;

import com.tsbg.ecosys.model.FriendlyLink;
import com.tsbg.ecosys.service.FriendlyLinkService;
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
    @ResponseBody
    public List<FriendlyLink> getSiteURL() {
        List<FriendlyLink> friendlyLinks = friendlyLinkService.selectPortalsiteUrl();
        return friendlyLinks;
    }
}
