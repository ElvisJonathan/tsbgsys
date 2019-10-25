package com.tsbg.mis.ecoServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tsbg.mis.ecoMapper.QuestionMapper;
import com.tsbg.mis.ecoModel.bag.DerivediPackage;
import com.tsbg.mis.ecoModel.bag.ProjectqfPackage;
import com.tsbg.mis.ecoService.QuestionService;
import com.tsbg.mis.util.PageRequest;
import com.tsbg.mis.util.PageResult;
import com.tsbg.mis.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{
   @Autowired
   private QuestionMapper questionMapper;

    @Override
    public List<DerivediPackage> selectquestion() {
        return questionMapper.selectquestion();
    }

    @Override
    public PageResult selectbasePage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, getPagebase(pageRequest));
    }

    private PageInfo<ProjectqfPackage> getPagebase(PageRequest pageRequest) {
        int pageIndex = pageRequest.getPageIndex();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageIndex, pageSize);
        //此处实现了加搜索条件情况下的分页,如果不需要搜索则不需要加实体类传参
        List<ProjectqfPackage> sysMenus = questionMapper.selectbasePage();
        return new PageInfo<ProjectqfPackage>(sysMenus);
    }
}
