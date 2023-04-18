package com.project.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminLoginController 
{
	public static int k=0;
	@RequestMapping("/admin")
	public String admin()
	{
		k=0;
		return "adminlogin.html";
	}
	
	@RequestMapping("/adminLogin" )
	public String adminLogin(@RequestParam("id")String name,@RequestParam("psw")String password,Model model)
	{
		 if((name.equals("admin1") && password.equals("admin1")) ||
			(name.equals("admin2") && password.equals("admin2")) ||
			(name.equals("admin3") && password.equals("admin3")))
		 { 
			k=1;
			return "redirect:/adminHome";
		 }
		model.addAttribute("error","Entered Invalid Username or Password");
		return "adminlogin.html";
	}
	
    @RequestMapping("/adminHome")
    public String adminHome()
    {
    	if(k==0) return "adminlogin.html";
    	return "adminHome.html";
    }
    
}
