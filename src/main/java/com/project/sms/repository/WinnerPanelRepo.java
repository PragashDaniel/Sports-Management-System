package com.project.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.sms.entity.WinnerPanel;

public interface WinnerPanelRepo extends JpaRepository<WinnerPanel,Long>
{
	@Query(value="select * from winner_panel where game=?",nativeQuery=true)
	List<WinnerPanel> searchByGame(String keyword);
}
