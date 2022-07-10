package com.jinseong.blog.controller.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jinseong.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@Value("${ck-editor.image-upload.path}")
	private String imageUploadPath;

	// 컨트롤러에서 세션을 어떻게 찾는지
	// @AuthenticationPrincipal PrincipalDetail principal

	/*
	 * @GetMapping("/board/boardList") public String board(@PageableDefault(size =
	 * 5, sort = "id", direction = Direction.DESC) Pageable pageable, Model model) {
	 * model.addAttribute("boards", boardService.findBoardAll(pageable)); return
	 * "board/boardList"; //viewResolver 작동! }
	 */

	@GetMapping("/board/boardList")
	public String boardGroupByCategory(@RequestParam(name = "category", defaultValue = "0") int id,
			@PageableDefault(size = 5, sort = "id", direction = Direction.DESC) Pageable pageable, Model model) {
		model.addAttribute("category", id);
		model.addAttribute("boards", boardService.findBoardAllByCategory(id, pageable));
		return "board/boardList"; // viewResolver 작동!
	}

	// USER 권한이 필요
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

	// USER 권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}

	// ck 에디터에서 파일 업로드
	@PostMapping("/board/ckUpload")
	public void CKEditorImgUpload(HttpServletRequest req, HttpServletResponse res,
			@RequestParam MultipartFile upload) throws Exception {
			boardService.CKEditorImgUpload(req,res,upload);
	}

}
