package com.project.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.sms.entity.Events;

public interface EventsRepository extends JpaRepository<Events,Long>
{
	@Query(value="Select * from events where game=?",nativeQuery=true)
	List<Events> searchByTeam(String keyword);
}
