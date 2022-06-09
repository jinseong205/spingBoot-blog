package com.jinseong.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jinseong.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//컨트롤러에서 세션을 어떻게 찾는지
	//@AuthenticationPrincipal PrincipalDetail principal

	@GetMapping({"/board/boardList","/board/boardList"})
	public String board(@PageableDefault(size = 5, sort = "id", direction = Direction.DESC) Pageable pageable, Model model) {
		model.addAttribute("boards", boardService.findBoardAll(pageable));
		return "board/boardList";	//viewResolver 작동!
	}

	//USER 권한이 필요
	@GetMapping("/board/boardDetail/{id}")
	public String boardDetail(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.boardDetail(id));
		return "board/boardDetail";
	}
	
	@GetMapping("/board/updateForm/{id}")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.boardDetail(id));
		return "board/updateForm";
	}
	
	//USER 권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
	

}
