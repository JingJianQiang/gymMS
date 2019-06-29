package com.gymMS.controller;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gymMS.pojo.User;
import com.gymMS.service.UserRoleService;
import com.gymMS.service.UserService;

@Controller
public class UserController {

//	@Autowired UserMapper userMapper;
	@Autowired UserService userService;
	@Autowired UserRoleService userRoleService;
	
//	请求修改密码
	@RequestMapping("/changePassword")
	public String changePassword(Model m,HttpServletRequest request)throws Exception{
//		User user=userMapper.getUserById(user_id);
		HttpSession session=request.getSession();
		String user_account=(String) session.getAttribute("user_account");
		User user=userService.getByAccount(user_account);
		m.addAttribute("user",user);
		return "changePassword";
	}
	
//	提交修改密码
	@RequestMapping("/updatePassword")
	public String changePasswor(User user)throws Exception{
//		userMapper.changePasswordById(user);
		userService.changePassword(user);
		return "redirect:userManage";
	}

	//删除用户
	@RequestMapping("/deleteUserById")
	public String deleteUserById(int user_id)throws Exception{
//		User user=userMapper.getUserById(user_id);
//		userMapper.deleteUserById(user.getUser_id());
		userService.delete(user_id);
		return "redirect:userManage";
	}
	//恢复用户
	@RequestMapping("/recoverUserById")
	public String recoverUserById(int user_id)throws Exception{
//		User user=userMapper.getUserById(user_id);
//		userMapper.recoverUserById(user.getUser_id());
		userService.recover(user_id);
		return "redirect:userManage";
	}
	
	//提交修改
	@RequestMapping("/updateById")
	public String updateById(User user,HttpServletRequest request,Model m)throws Exception{
		HttpSession session=request.getSession();
		int role_id=(int) session.getAttribute("role_id");
		System.out.println(role_id);
		if (role_id==2) {//系统管理员修改用户
			userService.update(user);
			return "redirect:userManage";
		}
		else {//其他用户
			userService.update(user);
			return "hello";			
		}
	}
	
	//请求编辑用户信息
	@RequestMapping("/editUser")
	public String editUser(int user_id,Model m)throws Exception{
//		User user=userMapper.getUserById(user_id);
		User user=userService.getUser(user_id);
		m.addAttribute("user",user);
		return "editUser";
	}
	
	//个人中心
	@RequestMapping("/personalCenter")
	public String personalCenter(String user_name,Model m)throws Exception{
		User user=userService.getByName(user_name);
		m.addAttribute("user",user);
		return "personalCenter";
	}
	
	
	//显示所有用户
	@RequestMapping("/userManage")
	public String userManager(Model m,@RequestParam(value="start",defaultValue="0")int start,
			@RequestParam(value="size",defaultValue="5")int size)throws Exception{
		PageHelper.startPage(start,size,"user_id");
//		List<User> us =userMapper.findAll();
		List<User> us =userService.list();
		PageInfo<User> page=new PageInfo<>(us);
		m.addAttribute("page",page);
		return "userManage";
	}
	
//	退出登录
	@RequestMapping("/logout")
	public String Logout(HttpServletRequest request) throws Exception{
		HttpSession session=request.getSession();
		session.invalidate();
		return "loginPage";
	}
	
//	简介
    @RequestMapping("/hello")
    public String hello(Model m) throws Exception{
    	SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
    	Date d=new Date();
    	String time = sdf.format(d);
    	m.addAttribute("time",time);
    	return "hello";
    }
	
}
