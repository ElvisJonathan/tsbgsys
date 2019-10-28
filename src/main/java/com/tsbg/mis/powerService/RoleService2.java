package com.tsbg.mis.powerService;

import com.tsbg.mis.powerModel.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleService2 {

    //通过userCode查找用户角色信息
    List<Role> findRoleByUserCode(@Param("userCode") String userCode);

    //通过userCode查找用户角色信息
    List<Integer> findRoleByUserCode2(@Param("userCode") String userCode);
}
