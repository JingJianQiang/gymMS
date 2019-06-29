package com.gymMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {
 
    @RequestMapping("/hello")
    public String hello() {
        return "Start gym Management System Poject!";
    }
    
    @RequestMapping("/test")
    public String test() {
    	return "testLayout";
    }

}
