package com.gymMS.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymMS.dao.RoleMapper;
import com.gymMS.dao.UserRoleMapper;

import com.gymMS.pojo.UserRole;
import com.gymMS.service.UserRoleService;

@Service
public class UserRoleServiceImp implements UserRoleService{
	
	@Autowired UserRoleMapper userRoleMapper;
	@Autowired RoleMapper roleMapper;
	
	@Override
	public List<UserRole> list() {
		List<UserRole> userRole=userRoleMapper.findAll();
		return userRole;
	}

	@Override
	public void addRole(UserRole userRole) {
		userRoleMapper.addRecord(userRole);
	}

	@Override
	public List<UserRole> getByUser(int user_id) {
		List<UserRole> userRole=userRoleMapper.findRole(user_id);
		return userRole;
	}
	
	@Override
	public List<UserRole> getByRole(int role_id) {
		List<UserRole> userRole=userRoleMapper.findUser(role_id);
		return userRole;
	}

	@Override
	public void changeRole(UserRole userRole) {
		userRoleMapper.updateRole(userRole);		
	}

	@Override
	public void deleteByUser(int user_id) {
		userRoleMapper.deleteByUserId(user_id);
	}

	@Override
	public int getRoleId(int user_id) {
		int role_id=userRoleMapper.findRoleId(user_id); 
//异常Mapper method 'com.gymMS.dao.UserRoleMapper.findRoleId attempted to return null from a method with a primitive return type (int).
//finroleid查询加入 ifnull（max（role_id），0）解决
//		String roleName=roleMapper.getNameById(role_id);
//		if (roleName==null) {
//			return 0;
//		}
		return role_id;
	}

//	@Override
//	public void delete(int user_id) {
//		userRoleMapper.delete(user_id);
//		
//	}
//
//	@Override
//	public void recover(int user_id) {
//		userRoleMapper.recover(user_id);
//		
//	}


}
