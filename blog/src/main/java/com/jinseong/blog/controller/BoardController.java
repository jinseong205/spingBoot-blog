package com.jinseong.blog.controller;

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
	public void postCKEditorImgUpload(HttpServletRequest req, HttpServletResponse res,
			@RequestParam MultipartFile upload) throws Exception {

		String uploadPath = req.getSession().getServletContext().getRealPath("image/board");
		
				// 랜덤 문자 생성
		UUID uid = UUID.randomUUID();

		OutputStream out = null;
		PrintWriter printWriter = null;

		// 인코딩
		res.setCharacterEncoding("utf-8");
		res.setContentType("application/json");

		try {

			String fileName = upload.getOriginalFilename(); // 파일 이름 가져오기
			byte[] bytes = upload.getBytes();
			
			
			// 업로드 경로
			String ckUploadPath = uploadPath + File.separator  + uid + "_" + fileName;
			
			
			System.out.println(uploadPath);
			System.out.println(ckUploadPath);
			
			File uploadFile = new File(ckUploadPath);
			uploadFile.createNewFile();
			out = new FileOutputStream(uploadFile);
			
			out.write(bytes);
			out.flush(); // out에 저장된 데이터를 전송하고 초기화

			// String callback = req.getParameter("CKEditorFuncNum");
			printWriter = res.getWriter();
			String fileUrl = uid + "_" + fileName; // 작성화면
			// 업로드시 메시지 출력
			JSONObject json = new JSONObject();
			json.put("uploaded", 1);	
			json.put("fileName", fileName);
			json.put("url", fileUrl);
			printWriter.println(json);

			printWriter.flush();
			System.out.println("test url : " + req.getSession().getServletContext().getRealPath("resouces/ckUpload"));
			System.out.println("url : " + fileUrl);
			System.out.println("ckUploadPath : " + ckUploadPath);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (printWriter != null) {
					printWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return;
	}

}
