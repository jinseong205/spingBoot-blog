package com.jinseong.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jinseong.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
 
}
