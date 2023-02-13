package com.file.upload.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.file.upload.entity.AddEvents;
import com.file.upload.entity.Attachment;
import com.file.upload.entity.WinnerPanel;
import com.file.upload.repository.AddEventsRepo;
import com.file.upload.repository.AttachmentRepository;
import com.file.upload.repository.WinnerPanelRepo;

@Service
public class AttachmentServiceImpl implements AttachmentService{

    private AttachmentRepository attachmentRepository;

    
    @Autowired
    private AddEventsRepo addEvents;
    @Autowired
    private WinnerPanelRepo winnerRepo;
    public AttachmentServiceImpl(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public Attachment saveAttachment(Attachment attachmentModel,MultipartFile file1,MultipartFile file2,MultipartFile file3) throws Exception {
       String fileName1 = StringUtils.cleanPath(file1.getOriginalFilename());
       String fileName2 = StringUtils.cleanPath(file2.getOriginalFilename());
       String fileName3=StringUtils.cleanPath(file3.getOriginalFilename());
       try {
            if(fileName1.contains("..") && fileName2.contains("..") && fileName3.contains(". .")) {
                throw  new Exception("Filename contains invalid path sequence "
                + fileName1+"or"+fileName2+"or"+fileName3);
            }

            Attachment attachment
                    = new Attachment(attachmentModel,fileName1,fileName2,fileName3,
                    file1.getContentType(),file2.getContentType(),file3.getContentType(),
                    file1.getBytes(),file2.getBytes(),file3.getBytes(),attachmentModel.getStatus());
            return attachmentRepository.save(attachment);

       } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName1+"or"+fileName2+"or"+fileName3);
       }
    }
    
    @Override
    public AddEvents saveEvent(AddEvents addEvent,MultipartFile file) throws Exception {
       String fileName1 = StringUtils.cleanPath(file.getOriginalFilename());
       try {
            if(fileName1.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                + fileName1);
            }

            AddEvents add
                    = new AddEvents(addEvent,fileName1,
                    file.getContentType(),
                    file.getBytes());
            return addEvents.save(add);

       } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName1);
       }
    }

    @Override
    public WinnerPanel saveWinner(WinnerPanel wp,MultipartFile file) throws Exception 
    {
       String fileName1 = StringUtils.cleanPath(file.getOriginalFilename());
       try {
            if(fileName1.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                + fileName1);
            }

            WinnerPanel add
                    = new WinnerPanel(wp,fileName1,
                    file.getContentType(),
                    file.getBytes());
            return winnerRepo.save(add);

       } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName1);
       }
    }
    
    @Override
    public Attachment getAttachment(Long fileId) throws Exception {
    	attachmentRepository.findById(fileId).ifPresent(System.out::println);
    		return attachmentRepository
                .findById(fileId)
                .orElseThrow(
                        () -> new Exception("File not found with Id: " + fileId));
    }

    @Override
    public AddEvents getEvent(Long Id) throws Exception {
    	addEvents.findById(Id).ifPresent(System.out::println);
    		return addEvents
                .findById(Id)
                .orElseThrow(
                        () -> new Exception("File not found with Id: " + Id));
    }
	@Override
	public List<Attachment> getAll(String keyword) 
	{		
		if(keyword!=null)
		{
			return attachmentRepository.search(keyword);
		}
		return attachmentRepository.findAll();
	}
	@Override
	public List<Attachment> getAll() 
	{		
		return attachmentRepository.inactive();
	}
	
	@Override
	public void changeStatus(Long id)
	{
		Attachment attach=attachmentRepository.findById(id).orElse(new Attachment());
		attach.setStatus("active");
		attachmentRepository.save(attach);
	
	}
	@Override
	public List<Attachment> getAllSelectedPlayers(String keyword) 
	{		
		if(keyword!=null)
		{
			return attachmentRepository.searchActive(keyword);
		}
		return attachmentRepository.findAll();
	}
	
	@Override
	public List<Attachment> getAllActive() 
	{		
		return attachmentRepository.active();
	}
	
	@Override
	public List<AddEvents> getAllSelectedEvents(String keyword)
	{
		if(keyword!=null)
		{
			return addEvents.searchByTeam(keyword);
		}
		return addEvents.findAll();
	}
	
	@Override
	public List<WinnerPanel> getAllWinners(String keyword)
	{
		if(keyword!=null)
		{
			return winnerRepo.searchByGame(keyword);
		}
		return winnerRepo.findAll();
	}
}
