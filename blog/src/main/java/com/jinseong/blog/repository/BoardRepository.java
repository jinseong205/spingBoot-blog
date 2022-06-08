package com.jinseong.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jinseong.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

	
}
