package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.mapper.FriendlyLinkMapper;
import com.tsbg.ecosys.model.FriendlyLink;
import com.tsbg.ecosys.service.FriendlyLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendlyLinkServiceImpl implements FriendlyLinkService {

    @Autowired
    private FriendlyLinkMapper friendlyLinkMapper;


    @Override
    public List<FriendlyLink> selectPortalsiteUrl() {
        return friendlyLinkMapper.selectPortalsiteUrl();
    }
}
