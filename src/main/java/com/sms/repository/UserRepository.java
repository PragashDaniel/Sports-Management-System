package com.project.sms.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import com.project.sms.entity.Users;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<Users, Long> 
{
	@Query(value="SELECT * FROM users WHERE game=? and status='inactive'",nativeQuery=true)
    public List<Users> search(String keyword);
	
	@Query(value="SELECT * FROM users WHERE status='inactive'",nativeQuery=true)
    public List<Users> inactive();
	
	@Query(value="SELECT * FROM users WHERE game=? and status='active'",nativeQuery=true)
    public List<Users> searchActive(String keyword);
		
	@Query(value="SELECT * FROM users WHERE status='active'",nativeQuery=true)
    public List<Users> active();
}
