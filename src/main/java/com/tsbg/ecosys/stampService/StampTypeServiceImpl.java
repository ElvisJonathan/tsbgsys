package com.tsbg.ecosys.stampService;


import com.tsbg.ecosys.stampMapper.StampTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StampTypeServiceImpl implements StampTypeService {

   @Autowired
    private StampTypeMapper stampTypeMapper;


    @Override
    public List<String> selectTypeName() {
        return stampTypeMapper.selectTypeName();
    }
}
