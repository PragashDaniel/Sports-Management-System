package com.project.sms.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.project.sms.entity.Events;
import com.project.sms.entity.Users;
import com.project.sms.entity.WinnerPanel;

public interface Services 
{
    public Users saveAttachment(Users a,MultipartFile file1,MultipartFile file2,MultipartFile file3) throws Exception;

    public Events saveEvent(Events addEvents,MultipartFile file) throws Exception;
    
    public Users getAttachment(Long fileId) throws Exception;
     
    public List<Users> getAll(String keyword);
    
    public List<Users> getAll();
    
    public void changeStatus(Long id);

    public List<Users> getAllSelectedPlayers(String keyword);

    public List<Users> getAllActive();

    public List<Events> getAllSelectedEvents(String keyword);

    public List<WinnerPanel> getAllWinners(String keyword);
 
    public Events getEvent(Long Id) throws Exception;

    public WinnerPanel saveWinner(WinnerPanel wp, MultipartFile file) throws Exception;
  
}
