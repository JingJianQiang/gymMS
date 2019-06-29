package com.gymMS.service;

import java.util.List;

import com.gymMS.pojo.Role;
import com.gymMS.pojo.User;

public interface RoleService {
	public List<Role> list();
	
	public List<String> list(User user);
	
	public Role getRole(int role_id);
	
	public int getRoleId(String role_name);
	
	public void addRole(Role role);
	
	public void changeDescription(Role role);
}
