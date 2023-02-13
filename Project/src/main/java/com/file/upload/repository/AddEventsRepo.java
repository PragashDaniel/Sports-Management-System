package com.file.upload.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.file.upload.entity.AddEvents;
import com.file.upload.entity.Attachment;

public interface AddEventsRepo extends JpaRepository<AddEvents,Long>
{
	@Query(value="Select * from add_events where game=?",nativeQuery=true)
	List<AddEvents> searchByTeam(String keyword);

}
