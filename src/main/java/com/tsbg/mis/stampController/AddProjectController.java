package com.tsbg.mis.stampController;

import com.tsbg.mis.annotation.PassToken;
import com.tsbg.mis.stampService.AddProjectService;
import com.tsbg.mis.stampVo.AddProjectVo;
import com.tsbg.mis.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/stamp/add")
public class AddProjectController {

    @Autowired
    private AddProjectService addProjectService;

    /**
     * 根据工号查出员工信息
     */
    @RequestMapping("selectUserInfo")
    @ResponseBody
    @PassToken
    public Object selectUserInfoByUserCode(@RequestBody AddProjectVo addProjectVo){
        String userCode = addProjectVo.getUserCode();
        int count = addProjectService.selectUserCode(userCode);
        if(count<1){
            return new ResultUtils(500,"该用户不存在");
        }
        return new ResultUtils(100,"请求成功",addProjectService.selectUserInfoByUserCode(userCode));
    }

    /**
     *  新增用印信息
     */
    @RequestMapping("insert")
    @ResponseBody
    @PassToken
    public Object insert(HttpServletRequest req, MultipartFile[] file) throws Exception {
        return addProjectService.insert(req, file);
    }

    /**
     *  查询所有厂区
     */
    @RequestMapping("selectFactory")
    @ResponseBody
    @PassToken
    public ResultUtils selectFactory(Map<String, Object> map){
        return new ResultUtils(100,"查询厂区成功",addProjectService.selectFactory(map));
    }

    /**
     *  查询所有事业群
     */
    @RequestMapping("selectBg")
    @ResponseBody
    @PassToken
    public ResultUtils selectBg(Map<String, Object> map){
        return new ResultUtils(100,"查询事业群成功",addProjectService.selectBg(map));
    }

    /**
     *  查询所有事业群处
     */
    @RequestMapping("selectBu")
    @ResponseBody
    @PassToken
    public ResultUtils selectBu(Map<String, Object> map){
        return new ResultUtils(100,"查询事业处成功",addProjectService.selectBu(map));
    }

    /**
     *  查询所有印章类别
     */
    @RequestMapping("selectTypeName")
    @ResponseBody
    @PassToken
    public ResultUtils selectTypeName(Map<String, Object> map){
        return new ResultUtils(100,"查询印章类别成功",addProjectService.selectTypeName(map));
    }

}
