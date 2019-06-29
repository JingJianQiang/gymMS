package com.gymMS.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value=Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req,Exception e)throws Exception{
		ModelAndView mav=new ModelAndView();
		mav.addObject("exception",e);
		mav.addObject("url",req.getRequestURL());//返回网址   http://127.0.0.1:8080/hello
//		mav.addObject("url",req.getRequestURI());  返回路径  /hello
		mav.setViewName("errorPage");
		return mav;
	}
}
