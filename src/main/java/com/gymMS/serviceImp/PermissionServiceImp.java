package com.gymMS.serviceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymMS.dao.PermissionMapper;
import com.gymMS.dao.RoleMapper;
import com.gymMS.dao.RolePermissionMapper;
import com.gymMS.pojo.Permission;
import com.gymMS.pojo.Role;
import com.gymMS.pojo.RolePermission;
import com.gymMS.service.PermissionService;
import com.gymMS.service.RoleService;


@Service
public class PermissionServiceImp implements PermissionService{
	
	@Autowired PermissionMapper permissionMapper;
	@Autowired RolePermissionMapper rolePermissionMapper;
	@Autowired RoleService roleService;
	
	@Override
	public List<Permission> list() {
		List<Permission> permission=permissionMapper.findAll();
		return permission;
	}

	@Override
	public Permission getPermissiom(int permission_id) {
		Permission permission=permissionMapper.getPermissionById(permission_id);
		return permission;
	}

	@Override
	public int getPermissionId(String permission_name) {
		int permissionId=permissionMapper.getIdByName(permission_name);
		return permissionId;
	}

	@Override
	public void addPermission(Permission permission) {
		permissionMapper.addPermission(permission);
	}

	@Override
	public void changeDescription(Permission permission) {
		permissionMapper.changePermission(permission);
	}

//	根据role 查permission
	@Override
	public List<String> list(Role role) {
		List<String> result = new ArrayList<>();
		List<RolePermission> rps = rolePermissionMapper.findPermission(role.getRole_id());//根据角色id查role_permission表
		for (RolePermission rolePermission : rps) {
			result.add(permissionMapper.getNameById(rolePermission.getPermission_id()));//根据permission_id查name存入list
		}
		return result;	
	}


}
