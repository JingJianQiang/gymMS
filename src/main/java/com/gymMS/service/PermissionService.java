package com.gymMS.service;

import java.util.List;

import com.gymMS.pojo.Permission;
import com.gymMS.pojo.Role;

public interface PermissionService {
	public List<Permission> list();
	
	public List<String> list(Role role);
	
	public Permission getPermissiom(int permission_id);
	
	public int getPermissionId(String permission_name);
	
	public void addPermission(Permission permission);

	public void changeDescription(Permission permission);
}
