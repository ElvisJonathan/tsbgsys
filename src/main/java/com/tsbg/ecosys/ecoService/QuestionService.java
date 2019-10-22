package com.tsbg.ecosys.ecoService;

import com.tsbg.ecosys.ecoModel.bag.DerivediPackage;
import com.tsbg.ecosys.util.PageRequest;
import com.tsbg.ecosys.util.PageResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionService {
    //导出反馈人、处理人信息查询
    List<DerivediPackage> selectquestion();
    //分页查询反馈人、处理人信息查询
    PageResult selectbasePage(@Param("pageRequest") PageRequest pageRequest);

}
