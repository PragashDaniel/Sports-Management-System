package com.project.sms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.project.sms.entity.Events;
import com.project.sms.entity.Users;
import com.project.sms.entity.WinnerPanel;
import com.project.sms.repository.UserRepository;
import com.project.sms.repository.EventsRepository;
import com.project.sms.repository.WinnerPanelRepo;

@Service
public class ServiceImplementation implements Services {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EventsRepository eventRepository;
	@Autowired
	private WinnerPanelRepo winnerRepository;

	public ServiceImplementation(UserRepository attachmentRepository) {
		this.userRepository = attachmentRepository;
	}

	@Override
	public Users saveAttachment(Users user, MultipartFile file1, MultipartFile file2, MultipartFile file3)
			throws Exception {
		String fileName1 = StringUtils.cleanPath(file1.getOriginalFilename());
		String fileName2 = StringUtils.cleanPath(file2.getOriginalFilename());
		String fileName3 = StringUtils.cleanPath(file3.getOriginalFilename());
		try {
			if (fileName1.contains("..") && fileName2.contains("..") && fileName3.contains(". .")) {
				throw new Exception(
						"Filename contains invalid path sequence " + fileName1 + "or" + fileName2 + "or" + fileName3);
			}
			Users attachment = new Users(user, fileName1, fileName2, fileName3, file1.getContentType(),
					file2.getContentType(), file3.getContentType(), file1.getBytes(), file2.getBytes(),
					file3.getBytes(), user.getStatus());
			return userRepository.save(attachment);

		} catch (Exception e) {
			throw new Exception("Could not save File: " + fileName1 + "or" + fileName2 + "or" + fileName3);
		}
	}

	@Override
	public Events saveEvent(Events addEvent, MultipartFile file) throws Exception {
		String fileName1 = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (fileName1.contains("..")) {
				throw new Exception("Filename contains invalid path sequence " + fileName1);
			}

			Events add = new Events(addEvent, fileName1, file.getContentType(), file.getBytes());
			return eventRepository.save(add);

		} catch (Exception e) {
			throw new Exception("Could not save File: " + fileName1);
		}
	}

	@Override
	public WinnerPanel saveWinner(WinnerPanel wp, MultipartFile file) throws Exception {
		String fileName1 = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (fileName1.contains("..")) {
				throw new Exception("Filename contains invalid path sequence " + fileName1);
			}

			WinnerPanel add = new WinnerPanel(wp, fileName1, file.getContentType(), file.getBytes());
			return winnerRepository.save(add);

		} catch (Exception e) {
			throw new Exception("Could not save File: " + fileName1);
		}
	}

	@Override
	public Users getAttachment(Long fileId) throws Exception {
		userRepository.findById(fileId).ifPresent(System.out::println);
		return userRepository.findById(fileId)
				.orElseThrow(() -> new Exception("File not found with Id: " + fileId));
	}

	@Override
	public Events getEvent(Long Id) throws Exception {
		eventRepository.findById(Id).ifPresent(System.out::println);
		return eventRepository.findById(Id).orElseThrow(() -> new Exception("File not found with Id: " + Id));
	}

	@Override
	public List<Users> getAll(String keyword) {
		if (keyword != null) {
			return userRepository.search(keyword);
		}
		return userRepository.findAll();
	}

	@Override
	public List<Users> getAll() {
		return userRepository.inactive();
	}

	@Override
	public void changeStatus(Long id) {
		Users attach = userRepository.findById(id).orElse(new Users());
		attach.setStatus("active");
		userRepository.save(attach);

	}

	@Override
	public List<Users> getAllSelectedPlayers(String keyword) {
		if (keyword != null) {
			return userRepository.searchActive(keyword);
		}
		return userRepository.findAll();
	}

	@Override
	public List<Users> getAllActive() {
		return userRepository.active();
	}

	@Override
	public List<Events> getAllSelectedEvents(String keyword) {
		if (keyword != null) {
			return eventRepository.searchByTeam(keyword);
		}
		return eventRepository.findAll();
	}

	@Override
	public List<WinnerPanel> getAllWinners(String keyword) {
		if (keyword != null) {
			return winnerRepository.searchByGame(keyword);
		}
		return winnerRepository.findAll();
	}

}
