package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.mapper.QHandleMapper;
import com.tsbg.ecosys.model.QHandle;
import com.tsbg.ecosys.service.QHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QHandleServiceImpl implements QHandleService{
    @Autowired
    private QHandleMapper qHandleMapper;
    @Override
    public List<QHandle> selectqHandleall() {
        return qHandleMapper.selectqHandleall();
    }
}
