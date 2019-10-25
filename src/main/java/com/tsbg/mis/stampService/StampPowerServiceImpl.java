package com.tsbg.mis.stampService;

import com.tsbg.mis.stampMapper.StampPowerMapper;
import com.tsbg.mis.stampVo.StampPowerVo;
import com.tsbg.mis.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StampPowerServiceImpl implements StampPowerService{


    @Autowired
    private StampPowerMapper stampPowerMapper;

    /**
     * 根据工号带出姓名和部门
     * @param userCode
     * @return
     */
    @Override
    public ResultUtils addPowerMembers(String userCode) {
        System.out.println("收到的userCode:"+userCode);
        //根据工号带出姓名和部门
        StampPowerVo stampPowerVo = stampPowerMapper.selectNameAndDePartByUserCode(userCode);
        if (stampPowerVo!=null){
            stampPowerVo.setUserCode(userCode);
            return new ResultUtils(100,"成功查出姓名和部门",stampPowerVo);
        }
        return new ResultUtils(501,"该工号尚未注册无相关姓名和部门信息");
    }

    /**
     * 根据工号添加用印群组成员
     * @param userCode
     * @return
     */
    @Override
    public ResultUtils selectRoleByUserCode(String userCode) {
        System.out.println("要添加人员的userCode:"+userCode);
        //根据userCode查询user_id
        Integer userId = stampPowerMapper.selectUserIdByUserCode(userCode);
        System.out.println("获取的用户编号为："+userId);
        //根据用户编号和项目来查询对应的角色，如果角色为7则可以修改并且添加成功后改为8
        //如果角色为8则提示已经拥有查询导出全部的权限
        Integer roleId = stampPowerMapper.selectRoleByUidAndProJId(userId);
        System.out.println("角色编号："+roleId);
        if (roleId==7){
            int i = stampPowerMapper.updateStampRoleByUidAndProjID(8, userId);
            if (i==1){
                StampPowerVo stampPowerVo = stampPowerMapper.selectNameAndDePartByUserCode(userCode);
                stampPowerVo.setUserCode(userCode);
                return new ResultUtils(100,"添加成功",stampPowerVo);
            }
        }
        return new ResultUtils(500,"用户不是用印普通会员无需添加");
    }


    /**
     * 删除权限群组成员
     */
    @Override
    public ResultUtils deleteStampMemByUserCode(String userCode) {
        System.out.println("要删除人员的userCode:"+userCode);
        //根据userCode查询user_id
        Integer userId = stampPowerMapper.selectUserIdByUserCode(userCode);
        System.out.println("获取的用户编号为："+userId);
        int i = stampPowerMapper.updateStampRoleByUidAndProjID(7, userId);
        if (i>0){
            return new ResultUtils(100,"删除成功,该成员现已是用印普通用户");
        }
        return new ResultUtils(501,"删除失败");
    }

    /**
     * 查询用印权限群组列表
     * @return
     */
    @Override
    public ResultUtils selectPowerList() {
        List<StampPowerVo> stampPowerVos = stampPowerMapper.selectPowerList();
        return new ResultUtils(100,"查询用印群组列表成功",stampPowerVos);

    }

}
