package com.tsbg.ecosys.doorService;

import com.tsbg.ecosys.doorMapper.FriendLinkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendLinkServiceImpl implements FriendLinkService {

    @Autowired
    private FriendLinkMapper friendLinkMapper;

    @Override
    public List<String> selectLinkName() {
        return friendLinkMapper.selectLinkName();
    }
}
