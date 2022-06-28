package com.jinseong.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.jinseong.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
	
	@Modifying
	@Query(value="INSERT INTO reply(memberId, boardId, content, createDate) VALUES(?1,?2,?3,SYSDATE)", nativeQuery=true)
	int mSave(int memberId, int boardId, String Content);
}
