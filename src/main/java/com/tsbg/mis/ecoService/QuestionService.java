package com.tsbg.mis.ecoService;

import com.tsbg.mis.ecoModel.bag.DerivediPackage;
import com.tsbg.mis.util.PageRequest;
import com.tsbg.mis.util.PageResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionService {
    //导出反馈人、处理人信息查询
    List<DerivediPackage> selectquestion();
    //分页查询反馈人、处理人信息查询
    PageResult selectbasePage(@Param("pageRequest") PageRequest pageRequest);

}
