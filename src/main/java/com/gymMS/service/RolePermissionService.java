package com.gymMS.service;

import java.util.List;

import com.gymMS.pojo.RolePermission;

public interface RolePermissionService {
	public List<RolePermission> list();
	
	public void addPermission(RolePermission rolePermission);
	
	public List<Integer> getByRole(int role_id);
	
	public List<RolePermission> getByRoleId(int role_id);
	
	public List<RolePermission> getByPermission(int permission_id);
	
	public void changePermission(RolePermission rolePermission);
	
	public void deleteByRole(int role_id);
	
	public void deleteByPermission(int permission_id);
}
