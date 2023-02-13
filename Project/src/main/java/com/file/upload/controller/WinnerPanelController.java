package com.file.upload.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.file.upload.entity.AddEvents;
import com.file.upload.entity.WinnerPanel;
import com.file.upload.repository.WinnerPanelRepo;
import com.file.upload.service.AttachmentService;

@Controller
public class WinnerPanelController 
{
	@Autowired
	WinnerPanelRepo winnerRepo;
	
	@Autowired
	AttachmentService attachmentService;
	
	AdminLoginController obj=new AdminLoginController();
	@RequestMapping("/winnerpanel")
	public String winnerPanel()
	{
		if(obj.k==0) return "adminLogin.html";
		return "adminAddWinners";
	}

	
	@RequestMapping("/managewinnerpanel")
    public String getAll(Model model)
    {
		if(obj.k==0) return "adminLogin.html";
    	model.addAttribute("student",winnerRepo.findAll());
    	return "managewinnerpanel";
    }
    
    @RequestMapping("/filterwinners")
	public String filterActive(Model model,@RequestParam("keyword") String keyword)
	{
    	if(keyword.equals("All")) return "redirect:/managewinnerpanel";
    	List<WinnerPanel> list=attachmentService.getAllWinners(keyword);
    	model.addAttribute("student", list);
		return "managewinnerpanel";
	}
    
    @RequestMapping("/updatewinner/{id}")
    public String update(@PathVariable long id)
    {
    	winnerRepo.deleteById(id);
    	return "adminAddWinners";
    }
    
    @RequestMapping("/deletewinner/{id}")
    public String delete(@PathVariable long id)
    {
    	winnerRepo.deleteById(id);
    	return "redirect:/managewinnerpanel";
    }
    
    @RequestMapping("/winnerpaneldetails")
    public String addWinner(WinnerPanel winner,@RequestParam("file1")MultipartFile file) throws Exception
    {
    	WinnerPanel wp=attachmentService.saveWinner(winner,file);
        ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/event/")
                .path(wp.getId().toString());
    	return "adminAddWinners";
    }
    @RequestMapping(value="download/winner/{id}")
    public void formDataAsResEntity(@PathVariable("id") Long id,HttpServletResponse response) throws IOException
    {
    	java.util.Optional<WinnerPanel> addE=winnerRepo.findById(id);
    	if(addE.isPresent())
    	{
    		byte[] image=addE.get().getPhoto();
    		StreamUtils.copy(image,response.getOutputStream());
    	}
    }
}
