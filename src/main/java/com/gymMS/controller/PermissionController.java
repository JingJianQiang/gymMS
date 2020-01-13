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
import com.gymMS.pojo.Permission;
import com.gymMS.pojo.Role;
import com.gymMS.pojo.RolePermission;
import com.gymMS.service.PermissionService;
import com.gymMS.service.RolePermissionService;
import com.gymMS.service.RoleService;

@Controller
public class PermissionController {
	@Autowired RoleService roleService;
	@Autowired RolePermissionService rolePermissionService;
	@Autowired PermissionService permissionService;
	
	@RequestMapping("/permissionManage")
	public String permissionManage(Model m,@RequestParam(value="start",defaultValue="0")int start,
			@RequestParam(value="size",defaultValue="5")int size) throws Exception{
		PageHelper.startPage(start,size,"role_id");
		List<Role> rs =roleService.list();//获取所有角色
		PageInfo<Role> page=new PageInfo<>(rs);
		m.addAttribute("page",page);
//		使用HashMap时遇到一种情况，数据输出顺序会变，所以使用linkedHashMap,数据顺序不会改变
		Map<Role, List<String>> role_permission=new LinkedHashMap<>();//存入角色以及对应权限
		for(Role role:rs) {
			List<String> ps=permissionService.list(role);//根据角色查询权限
			role_permission.put(role,ps);
		}
		m.addAttribute("role_permission", role_permission);
		return "permissionManage";
	}
	
		
		//请求编辑权限信息
		@RequestMapping("/editPermission")
		public String editPermission(int role_id,Model m)throws Exception{
			Role role=roleService.getRole(role_id);//根据role_id查询role表  
			m.addAttribute("role",role);
			
			List<Permission> permission=permissionService.list();//查permission表获取所有权限
			m.addAttribute("permission",permission);
		
			List<String> ps=permissionService.list(role);//根据role查询role_permission表
			m.addAttribute("ps",ps);
			
			return "editPermission";
		}
	
		//提交权限修改
		@RequestMapping("/updatePermission")
		public String updateById(String[] permission_name,int role_id)throws Exception{
			rolePermissionService.deleteByRole(role_id);//先删除该角色所有权限
			if (null != permission_name)				//所选权限不为空
				for (String pn : permission_name) {
					int permission_id=permissionService.getPermissionId(pn);	//根据传入权限名查权限id
					RolePermission rolePermission = new RolePermission();
					rolePermission.setPermission_id(permission_id);
					rolePermission.setRole_id(role_id);
					rolePermissionService.addPermission(rolePermission);
				}	
			
			return "redirect:permissionManage";	
		}
	

		//删除
		@RequestMapping("/deletePermission")
		public String deletePermission(int role_id)throws Exception{
			rolePermissionService.deleteByRole(role_id);
			return "redirect:permissionManage";
		}
	
}
