package com.tsbg.ecosys.masterService;

import com.tsbg.ecosys.masterMapper.NationListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NationListServiceImpl implements NationListService {

    @Autowired
    private NationListMapper nationListMapper;

    @Override
    public List<String> selectNationName() {
        return nationListMapper.selectNationName();
    }
}
