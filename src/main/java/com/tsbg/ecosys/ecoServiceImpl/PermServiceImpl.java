package com.tsbg.ecosys.ecoServiceImpl;

import com.alibaba.fastjson.JSONObject;

import com.tsbg.ecosys.ecoMapper.PermMapper;
import com.tsbg.ecosys.ecoService.PermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 *   权限实现类
 */
@Service
public class PermServiceImpl implements PermService {

	@Autowired
	private PermMapper permMapper;

	/**
	 * 查询某用户的 角色  菜单列表   权限列表
	 */
	@Override
	public JSONObject getUserPermission(String username) {
		JSONObject userPermission = getUserPermissionFromDB(username);
		return userPermission;
	}

	/**
	 * 角色 权限
	 */
	@Override
	public JSONObject getMyUserPermission(String userCode,Integer projId) {
		JSONObject userPermission = permMapper.getMyUserPermission(userCode,projId);
		return userPermission;
	}

	/**
	 * 用户 权限
	 */
	@Override
	public JSONObject getMyUserPermission2(String userCode) {
		JSONObject userPermission = permMapper.getMyUserPermission2(userCode);
		return userPermission;
	}

	/**
	 * 从数据库查询用户权限信息
	 */
	private JSONObject getUserPermissionFromDB(String username) {
		JSONObject userPermission = permMapper.getUserPermission(username);
		//管理员角色ID为1
		int adminRoleId = 1;
		//如果是管理员
		String roleIdKey = "roleId";
		if (adminRoleId == userPermission.getIntValue(roleIdKey)) {
			//查询所有菜单  所有权限
			Set<String> menuList = permMapper.getAllMenu();
			Set<String> permissionList = permMapper.getAllPermission();
			userPermission.put("menuList", menuList);
			userPermission.put("permissionList", permissionList);
		}
		return userPermission;
	}
}
