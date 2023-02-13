package com.file.upload.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;

import com.file.upload.entity.AddEvents;
import com.file.upload.entity.Attachment;
import com.file.upload.entity.WinnerPanel;

public interface AttachmentService 
{
    public Attachment saveAttachment(Attachment a,MultipartFile file1,MultipartFile file2,MultipartFile file3) throws Exception;

    public AddEvents saveEvent(AddEvents addEvents,MultipartFile file) throws Exception;
    
    public Attachment getAttachment(Long fileId) throws Exception;
     
    public List<Attachment> getAll(String keyword);
    
    public List<Attachment> getAll();
    
    public void changeStatus(Long id);

	List<Attachment> getAllSelectedPlayers(String keyword);

	List<Attachment> getAllActive();

	public List<AddEvents> getAllSelectedEvents(String keyword);

	public List<WinnerPanel> getAllWinners(String keyword);

	public AddEvents getEvent(Long Id) throws Exception;

	public WinnerPanel saveWinner(WinnerPanel wp, MultipartFile file) throws Exception;

  
}
