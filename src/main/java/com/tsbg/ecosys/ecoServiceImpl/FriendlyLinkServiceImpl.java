package com.tsbg.ecosys.ecoServiceImpl;

import com.tsbg.ecosys.ecoMapper.FriendlyLinkMapper;
import com.tsbg.ecosys.ecoModel.FriendlyLink;
import com.tsbg.ecosys.ecoService.FriendlyLinkService;
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
