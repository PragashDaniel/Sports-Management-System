package com.file.upload.controller;

import java.io.IOException;
import java.sql.Blob;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.file.upload.entity.AddEvents;
import com.file.upload.entity.Attachment;
import com.file.upload.repository.AddEventsRepo;
import com.file.upload.service.AttachmentService;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Controller
public class AddEventsController 
{
	@Autowired
	AddEventsRepo addEvent;
	
	@Autowired
	AttachmentService attachmentService;
	
	AdminLoginController obj=new AdminLoginController();
	@RequestMapping("/addevents")
    public String addEvents()
    {
		if(obj.k==0) return "adminLogin.html";
    	return "adminAddEvents";
    }
	
    @RequestMapping("/addeventdetails")
    public String addEvents(AddEvents events,@RequestParam("file1")MultipartFile file) throws Exception
    {
    	AddEvents addEvents=attachmentService.saveEvent(events,file);
        ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/event/")
                .path(addEvents.getMeetid().toString());
    	return "adminAddEvents";
    }
    
   /* @RequestMapping(value="download/event/{fileId}", method=RequestMethod.GET)
    public ResponseEntity<Resource> downloadFile1(@PathVariable Long fileId) throws Exception {
        AddEvents add = null;
        add = attachmentService.getEvent(fileId);
        ResponseEntity<Resource> re1=ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/png"))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + add.getFileName()
                + "\"")
                .body(new ByteArrayResource(add.getEvent()));
                return re1;
    }*/
    
    
    @RequestMapping("/manageevents")
    public String getAll(Model model)
    {
    	if(obj.k==0) return "adminLogin.html";
    	model.addAttribute("student",addEvent.findAll());
    	return "manageevents";
    }
    
    @RequestMapping("/filterEvents")
	public String filterActive(Model model,@RequestParam("keyword") String keyword)
	{
    	if(keyword.equals("All")) return "redirect:/manageevents";
    	List<AddEvents> list=attachmentService.getAllSelectedEvents(keyword);
    	model.addAttribute("student", list);
		return "manageevents";
	}
    
    @RequestMapping("/updateevent/{id}")
    public String update(@PathVariable long id)
    {
    	addEvent.deleteById(id);
    	return "adminAddEvents";
    }
    
    @RequestMapping("/deleteevent/{id}")
    public String delete(@PathVariable long id)
    {
    	addEvent.deleteById(id);
    	return "redirect:/manageevents";
    }
    
    @RequestMapping(value="download/event/{id}")
    public void formDataAsResEntity(@PathVariable("id") Long id,HttpServletResponse response) throws IOException
    {
    	java.util.Optional<AddEvents> addE=addEvent.findById(id);
    	if(addE.isPresent())
    	{
    		byte[] image=addE.get().getEvent();
    		StreamUtils.copy(image,response.getOutputStream());
    	}
    }
    
}
