package com.tsbg.mis.questionService;

import com.tsbg.mis.questionModel.bag.DerivediPackage2;
import com.tsbg.mis.util.PageRequest;
import com.tsbg.mis.util.PageResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionService2 {
    //导出反馈人、处理人信息查询
    List<DerivediPackage2> selectquestion();

    //分页查询反馈人、处理人信息查询
    PageResult selectbasePage(@Param("pageRequest") PageRequest pageRequest);

}
