package com.gymMS.service;

import java.util.List;

import com.gymMS.pojo.UserRole;

public interface UserRoleService {
	public List<UserRole> list();
	
	public void addRole(UserRole userRole);
	
	public List<UserRole> getByUser(int user_id);
	
	public List<UserRole> getByRole(int role_id);
	
	public int getRoleId(int user_id);
	
	public void changeRole(UserRole userRole);
	
	public void deleteByUser(int user_id);
	
//	public void delete(int user_id);
//	
//	public void recover(int user_id);
}
