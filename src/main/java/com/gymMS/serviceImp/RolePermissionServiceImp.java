package com.gymMS.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymMS.dao.RolePermissionMapper;
import com.gymMS.pojo.RolePermission;
import com.gymMS.service.RolePermissionService;

@Service
public class RolePermissionServiceImp implements RolePermissionService {
	
	@Autowired RolePermissionMapper rolePermissionMapper;
	
	@Override
	public List<RolePermission> list() {
		List<RolePermission> rolePermission=rolePermissionMapper.findAll();
		return rolePermission;
	}

	@Override
	public void addPermission(RolePermission rolePermission) {
		rolePermissionMapper.addRecord(rolePermission);
		
	}

	@Override
	public List<Integer> getByRole(int role_id) {
		List<Integer> rolePermission=rolePermissionMapper.findPermissionId(role_id);
		return rolePermission;
	}

	@Override
	public List<RolePermission> getByRoleId(int role_id) {
		List<RolePermission> rolePermissions=rolePermissionMapper.findPermission(role_id);
		return rolePermissions;
	}

	@Override
	public List<RolePermission> getByPermission(int permission_id) {
		List<RolePermission> rolePermission=rolePermissionMapper.findRoleId(permission_id);
		return rolePermission;
	}

	@Override
	public void changePermission(RolePermission rolePermission) {
		rolePermissionMapper.updatePermission(rolePermission);
	}

	@Override
	public void deleteByRole(int role_id) {
		rolePermissionMapper.deleteByRoleId(role_id);
	}

	@Override
	public void deleteByPermission(int permission_id) {
		rolePermissionMapper.deleteByPermissionId(permission_id);
	}


}
