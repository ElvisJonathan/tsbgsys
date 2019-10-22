package com.tsbg.ecosys.assetsService;

import com.tsbg.ecosys.assetsMapper.AssetsTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetsTypeServiceImpl implements AssetsTypeService {

    @Autowired
    private AssetsTypeMapper assetsTypeMapper;

    @Override
    public List<String> selectAssetsType() {
        return assetsTypeMapper.selectAssetsType();
    }
}
