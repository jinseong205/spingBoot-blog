package com.jinseong.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jinseong.blog.auth.PrincipalDetail;
import com.jinseong.blog.dto.ResponseDto;
import com.jinseong.blog.model.Board;
import com.jinseong.blog.service.BoardService;

@RestController
public class BoardApiController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board	 board, @AuthenticationPrincipal PrincipalDetail principal) {
		boardService.saveBoard(board, principal.getMember());
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
		//return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	//@RequestMapping(value="/api/board/{id}", method=RequestMethod.DELETE)
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id) {
		boardService.deleteBoard(id);
		//return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}

	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {
		boardService.updateBoard(id, board);
		//return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}
}
