package com.gymMS.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gymMS.pojo.Role;
import com.gymMS.pojo.User;
import com.gymMS.pojo.UserRole;
import com.gymMS.service.RoleService;
import com.gymMS.service.UserRoleService;
import com.gymMS.service.UserService;

@Controller
public class RoleController {
	@Autowired UserService userService;
	@Autowired RoleService roleService;
	@Autowired UserRoleService userRoleService;
	
	@RequestMapping("/roleManage")
	public String roleManage(Model m,@RequestParam(value="start",defaultValue="0")int start,
			@RequestParam(value="size",defaultValue="5")int size) throws Exception{
		PageHelper.startPage(start,size,"user_id");
		List<User> us = userService.list();
		PageInfo<User> page=new PageInfo<>(us);
		m.addAttribute("page",page);
//		使用HashMap时遇到一种情况，数据输出顺序会变，所以使用linkedHashMap,数据顺序不会改变
		Map<User, List<String>> user_role=new LinkedHashMap<>();//存入角色以及对应权限
		for(User user:us) {
			List<String> rs=roleService.list(user);//根据用户查角色
			user_role.put(user,rs);
		}
		m.addAttribute("user_role", user_role);
		
		return "roleManage";
	}
	//请求编辑角色信息
	@RequestMapping("/editRole")
	public String editRole(int user_id,Model m)throws Exception{
		User user=userService.getUser(user_id);
		m.addAttribute("user",user);
		
		List<Role> role=roleService.list();
		m.addAttribute("role",role);
		
		List<String> rs=roleService.list(user);
		m.addAttribute("rs",rs);
		
		return "editRole";
	}
	
	//提交角色修改
	@RequestMapping("/updateRole")
	public String updateById(String role_name,int user_id)throws Exception{
		userRoleService.deleteByUser(user_id);
		if (null!=role_name) {
			int role_id=roleService.getRoleId(role_name);
			UserRole userRole=new UserRole();
			userRole.setRole_id(role_id);
			userRole.setUser_id(user_id);
			userRoleService.addRole(userRole);
		}
		
		return "redirect:roleManage";
	}
	
	
	//删除
	@RequestMapping("/deleteRole")
	public String deleteRole(int user_id)throws Exception{
		userRoleService.deleteByUser(user_id);
		return "redirect:roleManage";
	}
	
}
