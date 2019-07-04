package com.gymMS.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.gymMS.pojo.User;
import com.gymMS.service.UserRoleService;
import com.gymMS.service.UserService;

@Controller
public class LoginController {
	@Autowired UserService userService;
	@Autowired UserRoleService userRoleService;
	
	String msg="";
    @RequestMapping("/loginPage")
    public String loginPage(Model m) throws Exception{
    	return "loginPage";
    }
    
//    @RequestMapping("/UserHome")
//    public String UserHome(Model m,HttpServletRequest request)throws Exception{
//    	HttpSession session=request.getSession();
//    	if (null==session.getAttribute("user_account")) {
//			return "loginPage";
//		}
//    	User user=userService.getByAccount((String) session.getAttribute("user_account"));
//    	session.setAttribute("user_name", user.getUser_name());
//		return "UserHome";
//    }
    
    //登录
    @RequestMapping("/login")
    public String login(String user_account,String user_password,HttpServletRequest request,Model m)throws Exception{
    	
    	if (user_account.length()<6) {
			msg="请正确输入账号！";
			m.addAttribute("msg",msg);
			return "loginPage";
		}
    	User user=userService.getByAccount(user_account);
    	int role_id=userRoleService.getRoleId(user.getUser_id());

    	if (user==null||user.isIsdel()) {
    		msg="无此账号！";
			m.addAttribute("msg",msg);
			return "loginPage";
    	}
    	
    	if (user.getUser_password().equals(user_password)&&role_id>1) {
    		HttpSession session=request.getSession();
    		session.setAttribute("user_account",user.getUser_account());
    		session.setAttribute("role_id",role_id);
    		session.setAttribute("user_name", user.getUser_name());
    		return "ManageHome";
    	} 
    	
    	if (user.getUser_password().equals(user_password)&&role_id<2) {
    		HttpSession session=request.getSession();
    		session.setAttribute("user_account",user.getUser_account());
    		session.setAttribute("user_name", user.getUser_name());
    		session.setAttribute("role_id",role_id);
    		return "UserHome";
    	}
    	
    	else {
    		msg ="密码错误！";
			m.addAttribute("msg",msg);
			return "loginPage";
		}
    		
    }
    
    
    
}
