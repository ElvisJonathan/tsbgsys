package com.tsbg.mis.questionService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tsbg.mis.questionMapper.QuestionMapper2;
import com.tsbg.mis.questionModel.bag.DerivediPackage2;
import com.tsbg.mis.questionModel.bag.ProjectqfPackage2;
import com.tsbg.mis.util.PageRequest;
import com.tsbg.mis.util.PageResult;
import com.tsbg.mis.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl2 implements QuestionService2{

   @Autowired
   private QuestionMapper2 questionMapper;

    @Override
    public List<DerivediPackage2> selectquestion() {
        return questionMapper.selectquestion();
    }

    @Override
    public PageResult selectbasePage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, getPagebase(pageRequest));
    }

    private PageInfo<ProjectqfPackage2> getPagebase(PageRequest pageRequest) {
        int pageIndex = pageRequest.getPageIndex();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageIndex, pageSize);
        //此处实现了加搜索条件情况下的分页,如果不需要搜索则不需要加实体类传参
        List<ProjectqfPackage2> sysMenus = questionMapper.selectbasePage();
        return new PageInfo<ProjectqfPackage2>(sysMenus);
    }
}
