package com.file.upload.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.file.upload.entity.Attachment;
import com.file.upload.repository.AddEventsRepo;
import com.file.upload.repository.AttachmentRepository;
import com.file.upload.repository.WinnerPanelRepo;

@Controller
public class UserController 
{
	private int k=0;
	@Autowired
	AttachmentRepository attachment;
	
	@Autowired
	AddEventsRepo addEvent;
	
	@Autowired
	WinnerPanelRepo winner;
	
	Long id;
	@RequestMapping("/user")
	public String user()
	{
		k=0;
		return "userlogin.html";
	}
	
	@RequestMapping("/userHome")
    public String userHome()
    {
		if(k==0) return "redirect:/user";
    	return "userhome.html";
    }
	@RequestMapping("/userLogin" )
	public String userLogin(@RequestParam("id")Long id,@RequestParam("psw")Long password,Model model)
	{
		 boolean val=attachment.findById(id).isPresent();	 
		 if(val==true)
		 {		 
			 Optional<Attachment> a=attachment.findById(id);
			 if((a.get().getId().equals(id) && password.equals(a.get().getMobileNo())))
			 {
				this.id=id;
				k=1;
		 		return "redirect:/userHome";
			 }
			 else {
				model.addAttribute("error","Entered Invalid Username or Password");
				return "userlogin.html";}
		 }
		 else
		 	model.addAttribute("error","Entered Invalid Username or Password");
		return "userlogin.html";
	}
	
	@RequestMapping("/userDetails")
	public String UserDetails(Model model)
	{
		if(k==0)
		   return "userlogin.html";
		model.addAttribute("user",attachment.findById(id).get());
		return "userdetails.html";
		
	}
	
	@RequestMapping("/coplayers")
	public String coplayers(Model model)
	{
		if(k==0)
			   return "userlogin.html";
		if(attachment.findById(id).get().getStatus().equals("active")){
			model.addAttribute("user",attachment.searchActive(attachment.findById(id).get().getGame()));
			return "coplayers.html";
		}
		model.addAttribute("err","*** Sorry, You are not yet selected ***");
		return "userhome.html";
	}
	
	@RequestMapping("/events")
	public String events(Model model)
	{
		if(k==0)
			   return "userlogin.html";
		if(attachment.findById(id).get().getStatus().equals("active"))
		{
			model.addAttribute("student",addEvent.findAll());
			return "events.html";
		}
		model.addAttribute("err","Sorry, You not yet selected");
		return "userhome.html";
	}
	
	@RequestMapping("/studentWinners")
	public String studentWinners(Model model)
	{
		if(k==0)
			   return "userlogin.html";
		if(attachment.findById(id).get().getStatus().equals("active"))
		{
			model.addAttribute("student",winner.findAll());
			return "winners.html";
		}
		model.addAttribute("err","Sorry, You not yet selected");
		return "userhome.html";
	}
}
