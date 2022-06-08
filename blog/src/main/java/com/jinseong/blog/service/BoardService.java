package com.jinseong.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jinseong.blog.model.Board;
import com.jinseong.blog.model.Member;
import com.jinseong.blog.repository.BoardRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	
	@Transactional
	public void saveBoard (Board board, Member member) {	//title, content
		board.setCount(0);
		boardRepository.save(board);
	}

	public Page<Board> findBoardAll(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}


}
