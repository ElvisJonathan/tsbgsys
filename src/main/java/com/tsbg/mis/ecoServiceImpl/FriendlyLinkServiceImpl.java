package com.tsbg.mis.ecoServiceImpl;

import com.tsbg.mis.ecoMapper.FriendlyLinkMapper;
import com.tsbg.mis.ecoModel.FriendlyLink;
import com.tsbg.mis.ecoService.FriendlyLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendlyLinkServiceImpl implements FriendlyLinkService {

    @Autowired
    private FriendlyLinkMapper friendlyLinkMapper;

    //@RequiresPermissions("view")
    @Override
    public List<FriendlyLink> selectPortalsiteUrl() {
        return friendlyLinkMapper.selectPortalsiteUrl();
    }
}
