package com.file.upload.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import com.file.upload.entity.Attachment;

@Repository
@Transactional
public interface AttachmentRepository extends JpaRepository<Attachment, Long> 
{
	@Query(value="SELECT * FROM attachment WHERE game=? and status='inactive'",nativeQuery=true)
    public List<Attachment> search(String keyword);
	
	@Query(value="SELECT * FROM attachment WHERE status='inactive'",nativeQuery=true)
    public List<Attachment> inactive();
	
	@Query(value="SELECT * FROM attachment WHERE game=? and status='active'",nativeQuery=true)
    public List<Attachment> searchActive(String keyword);
	
	
	@Query(value="SELECT * FROM attachment WHERE status='active'",nativeQuery=true)
    public List<Attachment> active();
}
