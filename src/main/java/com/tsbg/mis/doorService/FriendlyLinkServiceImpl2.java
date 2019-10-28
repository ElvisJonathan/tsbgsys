package com.tsbg.mis.doorService;

import com.tsbg.mis.doorMapper.FriendlyLinkMapper2;
import com.tsbg.mis.doorModel.FriendlyLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendlyLinkServiceImpl2 implements FriendlyLinkService2 {

    @Autowired
    private FriendlyLinkMapper2 friendlyLinkMapper;

    @Override
    public List<FriendlyLink> selectPortalsiteUrl() {
        return friendlyLinkMapper.selectPortalsiteUrl();
    }
}
