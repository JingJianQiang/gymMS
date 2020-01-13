package com.gymMS.serviceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymMS.dao.RoleMapper;
import com.gymMS.dao.UserRoleMapper;
import com.gymMS.pojo.Role;
import com.gymMS.pojo.User;
import com.gymMS.pojo.UserRole;
import com.gymMS.service.RoleService;

@Service
public class RoleServiceImp implements RoleService{
	
	@Autowired RoleMapper roleMapper;
	@Autowired UserRoleMapper userRoleMapper;
	
	@Override
	public List<Role> list() {
		List<Role> role=roleMapper.findAll();
		return role;
	}

	@Override
	public List<String> list(User user) {
		List<String> result=new ArrayList<>();
		List<UserRole> urs= userRoleMapper.findRole(user.getUser_id());
		for(UserRole userRole:urs) {
			result.add(roleMapper.getNameById(userRole.getRole_id()));
		}
		return result;
	}

	@Override
	public Role getRole(int role_id) {
		Role role=roleMapper.getRoleById(role_id);
		return role;
	}
	
	@Override
	public int getRoleId(String role_name) {
		int role_id=roleMapper.getRoleId(role_name);
		return role_id;
	}

	@Override
	public void addRole(Role role) {
		roleMapper.addRole(role);
	}

	@Override
	public void changeDescription(Role role) {
		roleMapper.updateById(role);
	}


}
