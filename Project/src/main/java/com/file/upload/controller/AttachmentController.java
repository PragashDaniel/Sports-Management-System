package com.file.upload.controller;




import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.file.upload.entity.AddEvents;
import com.file.upload.entity.Attachment;
import com.file.upload.entity.WinnerPanel;
import com.file.upload.repository.AttachmentRepository;
import com.file.upload.service.AttachmentService;
import com.file.upload.controller.AdminLoginController;

@Controller
public class AttachmentController 
{
	@Autowired
	AttachmentRepository attachment;
	
	AdminLoginController obj=new AdminLoginController();
	
    private AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }
    
    @RequestMapping("/register")
    public String register()
    {
    	return "index.html";
    }
    

	@PostMapping("/upload")
    public ModelAndView uploadFile(Attachment attachmentModel, @RequestParam("file1") MultipartFile file1,
    		@RequestParam("file2")MultipartFile file2,
    		@RequestParam("file3") MultipartFile file3,Model model) throws Exception 
    {
    	
    	ModelAndView mv=new ModelAndView("index");
    	boolean var=attachment.findById(attachmentModel.getId()).isPresent();
    	if(var)
    	{
    		model.addAttribute("error","*** User Id already exist ***");
			return mv;
    	}
    	Attachment attachment=attachmentService.saveAttachment(attachmentModel,file1,file2,file3);
        ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/first/")
                .path(attachment.getId().toString());
        ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/download/second/")
                    .path(attachment.getId().toString());
        ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/download/third/")
        .path(attachment.getId().toString());
        model.addAttribute("e","Registration Succesfully Completed");
        return mv;
    }

    @RequestMapping(value="download/first/{fileId}", method=RequestMethod.GET)
    public ResponseEntity<Resource> downloadFile1(@PathVariable Long fileId) throws Exception {
        Attachment attachment = null;
        attachment = attachmentService.getAttachment(fileId);
        ResponseEntity<Resource> re1=ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/png"))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFileName1()
                + "\"")
                .body(new ByteArrayResource(attachment.getData1()));
                return re1;
    }
    @RequestMapping(value="download/second/{fileId}", method=RequestMethod.GET)
    public ResponseEntity<Resource> downloadFile2(@PathVariable Long fileId) throws Exception {
        Attachment attachment = null;
        attachment = attachmentService.getAttachment(fileId);
        ResponseEntity<Resource> re2=ResponseEntity.ok()
            .contentType(MediaType.parseMediaType("image/png"))
            .header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + attachment.getFileName2()
            + "\"")
            .body(new ByteArrayResource(attachment.getData2()));
        return re2;
    }
    @RequestMapping(value="download/third/{fileId}", method=RequestMethod.GET)
    public ResponseEntity<Resource> downloadFile3(@PathVariable Long fileId) throws Exception {
        Attachment attachment = null;
        attachment = attachmentService.getAttachment(fileId);
        ResponseEntity<Resource> re2=ResponseEntity.ok()
            .contentType(MediaType.parseMediaType("image/png"))
            .header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + attachment.getFileName3()
            + "\"")
            .body(new ByteArrayResource(attachment.getData3()));
        return re2;
    }
    @RequestMapping("/filter")
	public String listStudents(Model model,@RequestParam("keyword") String keyword)
	{
    	if(keyword.equals("All")) return "redirect:/students";
    	List<Attachment> list=attachmentService.getAll(keyword);
    	model.addAttribute("student", list);
    	model.addAttribute("keyword",keyword);
		return "view";
	}
    @RequestMapping("/students")
    public ModelAndView listStudents(Model model)
	{
    	if(obj.k==0) return new ModelAndView("adminLogin");
    	model.addAttribute("student", attachmentService.getAll());
		return new ModelAndView("view");
	}
    
    @RequestMapping("/change/{id}")
    public String updatestatus(@PathVariable long id)    
    { 	
    	attachmentService.changeStatus(id);
    	return "redirect:/students";
    }
    

    
    
    @RequestMapping("/filterActive")
	public String filterActive(Model model,@RequestParam("keyword") String keyword)
	{
    	if(keyword.equals("All")) return "redirect:/details";
    	List<Attachment> list=attachmentService.getAllSelectedPlayers(keyword);
    	model.addAttribute("student", list);
    	model.addAttribute("keyword",keyword);
		return "viewDetails";
	}
    
    @RequestMapping("/details")
    public ModelAndView listStudentsAll(Model model)
	{
    	if(obj.k==0) return new ModelAndView("adminlogin");
    	model.addAttribute("student", attachmentService.getAllActive());
		return new ModelAndView("viewDetails");
	}
    
    @RequestMapping(value="download/firstimg/{id}")
    public void formDataAsResEntity1(@PathVariable("id") Long id,HttpServletResponse response) throws IOException
    {
    	java.util.Optional<Attachment> addE=attachment.findById(id);
    	if(addE.isPresent())
    	{
    		byte[] image=addE.get().getData1();
    		StreamUtils.copy(image,response.getOutputStream());
    	}
    }
    
    @RequestMapping(value="download/secondimg/{id}")
    public void formDataAsResEntity2(@PathVariable("id") Long id,HttpServletResponse response) throws IOException
    {
    	java.util.Optional<Attachment> addE=attachment.findById(id);
    	if(addE.isPresent())
    	{
    		byte[] image=addE.get().getData2();
    		StreamUtils.copy(image,response.getOutputStream());
    	}
    }
    
    @RequestMapping(value="download/thirdimg/{id}")
    public void formDataAsResEntity3(@PathVariable("id") Long id,HttpServletResponse response) throws IOException
    {
    	java.util.Optional<Attachment> addE=attachment.findById(id);
    	if(addE.isPresent())
    	{
    		byte[] image=addE.get().getData3();
    		StreamUtils.copy(image,response.getOutputStream());
    	}
    }
    @RequestMapping("/update/{id}")
    public String update(@PathVariable long id)
    {
    
    	attachment.deleteById(id);
    	return "redirect:/register";
    }
    
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable long id)
    {
    	attachment.deleteById(id);
    	return "redirect:/details";
    }
}
