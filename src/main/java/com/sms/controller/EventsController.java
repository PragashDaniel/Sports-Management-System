package com.project.sms.controller;

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
import com.project.sms.entity.Events;
import com.project.sms.repository.EventsRepository;
import com.project.sms.service.Services;

@Controller
public class EventsController {
	@Autowired
	EventsRepository addEventRepo;

	@Autowired
	Services service;

	@RequestMapping("/addevents")
	public String addEvents() {
		if (AdminLoginController.k == 0)
			return "adminLogin.html";
		return "adminAddEvents";
	}

	@RequestMapping("/addeventdetails")
	public String addEvents(Events events, @RequestParam("file1") MultipartFile file) throws Exception {
		Events event = service.saveEvent(events, file);
		ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/event/")
				.path(event.getMeetid().toString());
		return "adminAddEvents";
	}

	/*
	 * @RequestMapping(value="download/event/{fileId}", method=RequestMethod.GET)
	 * public ResponseEntity<Resource> downloadFile1(@PathVariable Long fileId)
	 * throws Exception { AddEvents add = null; add =
	 * attachmentService.getEvent(fileId); ResponseEntity<Resource>
	 * re1=ResponseEntity.ok() .contentType(MediaType.parseMediaType("image/png"))
	 * .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
	 * add.getFileName() + "\"") .body(new ByteArrayResource(add.getEvent()));
	 * return re1; }
	 */

	@RequestMapping("/manageevents")
	public String getAll(Model model) {
		if (AdminLoginController.k == 0)
			return "adminLogin.html";
		model.addAttribute("student", addEventRepo.findAll());
		return "manageevents";
	}

	@RequestMapping("/filterEvents")
	public String filterActive(Model model, @RequestParam("keyword") String keyword) {
		if (keyword.equals("All"))
			return "redirect:/manageevents";
		List<Events> list = service.getAllSelectedEvents(keyword);
		model.addAttribute("student", list);
		return "manageevents";
	}

	@RequestMapping("/updateevent/{id}")
	public String update(@PathVariable long id) {
		addEventRepo.deleteById(id);
		return "adminAddEvents";
	}

	@RequestMapping("/deleteevent/{id}")
	public String delete(@PathVariable long id) {
		addEventRepo.deleteById(id);
		return "redirect:/manageevents";
	}

	@RequestMapping(value = "download/event/{id}")
	public void formDataAsResEntity(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
		java.util.Optional<Events> addE = addEventRepo.findById(id);
		if (addE.isPresent()) {
			byte[] image = addE.get().getEvent();
			StreamUtils.copy(image, response.getOutputStream());
		}
	}

}
