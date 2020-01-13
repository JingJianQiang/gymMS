package com.gymMS.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gymMS.pojo.User;
import com.gymMS.service.UserService;

@Controller
public class RegisterController {
	@Autowired UserService userService;
	String msg="";
	
	@RequestMapping("/registerPage")
	public String registerPage(Model m)throws Exception{
		return "registerPage";
	}
	
	@RequestMapping("/register")
	public String register(String user_account,String user_name,String user_password,String user_password2,String phone,HttpServletRequest request,Model m )throws Exception{
		if (userService.getByAccount(user_account)!=null) {
			msg="账号已存在！";
			m.addAttribute("msg",msg);
			return "registerPage";
		}
		
		else if (user_account.length()<6||user_account.length()>12) {
			msg="请输入6~12位的账号！";
			m.addAttribute("msg",msg);
			return "registerPage";
		}
		
		else if (user_password.length()<6||user_password.length()>12) {
			msg="请输入6~12位的密码！";
			m.addAttribute("msg",msg);
			return "registerPage";
		}
		
		else if (!user_password.equals(user_password2)) {
			msg="两次密码不一致，请正确输入！";
			m.addAttribute("msg",msg);
			return "registerPage";
		}
		
		else if (phone.length()!=11) {
			msg="请正确输入11位手机号！";
			m.addAttribute("msg",msg);
			return "registerPage";
		}
		
		else {
			User user=new User();
			user.setUser_account(user_account);
			user.setUser_name(user_name);
			user.setUser_password(user_password);
			user.setPhone(phone);
			userService.add(user);
			int role_id=0;
			HttpSession session=request.getSession();
			session.setAttribute("user_acount", user_account);
			session.setAttribute("user_name", user_name);
			session.setAttribute("role_id", role_id);
			return "UserHome";
		}
		
	}
	
}
