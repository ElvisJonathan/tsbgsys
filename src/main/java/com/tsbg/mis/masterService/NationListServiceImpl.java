package com.tsbg.mis.masterService;

import com.tsbg.mis.masterMapper.NationListMapperExam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NationListServiceImpl implements NationListService {

    @Autowired
    private NationListMapperExam nationListMapperExam;

    @Override
    public List<String> selectNationName() {
        return nationListMapperExam.selectNationName();
    }
}
