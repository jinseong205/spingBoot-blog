package com.jinseong.blog.service;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jinseong.blog.auth.PrincipalDetail;
import com.jinseong.blog.dto.ReplySaveRequestDto;
import com.jinseong.blog.model.Board;
import com.jinseong.blog.model.Member;
import com.jinseong.blog.model.Reply;
import com.jinseong.blog.model.RoleType;
import com.jinseong.blog.repository.BoardRepository;
import com.jinseong.blog.repository.MemberRepository;
import com.jinseong.blog.repository.ReplyRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private ReplyRepository replyRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Value("${ck-editor.image-upload.path}")
	private String imageUploadPath;

	@Transactional
	public void saveBoard(Board board, Member member) { // title, content
		board.setCount(0);
		board.setMember(member);
		boardRepository.save(board);
	}

	@Transactional(readOnly = true)
	public Page<Board> findBoardAll(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Page<Board> findBoardAllByCategory(int id, Pageable pageable) {
		return boardRepository.findBoardAllByCategory(id, pageable);
	}

	@Transactional(readOnly = true)
	public Board boardDetail(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 글을 찾을수 었습니다.");
		});
	}

	@Transactional
	public void deleteBoard(int id, PrincipalDetail principal) throws Exception {
		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 삭제 실패 : 게시글 id를 찾을 수 없습니다");
		});

		if (board.getMember().getId() != principal.getMember().getId()
				&& principal.getMember().getRole() != RoleType.ADMIN) {
			throw new Exception("글 삭제 실패 : 삭제 권한이 없습니다.");
		}

		boardRepository.deleteById(id);
	}

	@Transactional
	public void updateBoard(int id, Board requestBoard, PrincipalDetail principal) throws Exception {

		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 글을 찾을수 었습니다.");
		}); // 영속화 완료

		if (board.getMember().getId() != principal.getMember().getId()
				&& principal.getMember().getRole() != RoleType.ADMIN) {
			throw new Exception("글 수정 실패 : 수정 권한이 없습니다.");
		}

		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		board.setCategory(requestBoard.getCategory());

		// 해당 함수로 종료시에 Service가 종료될 때 트랜잭션이 종료됩니다.
		// 이때 더티 체킹 - 자동 업데이트가 됨. db flush
	}

	@Transactional
	public void saveReply(Reply reply, int boardId, Member member) {

		Board board = boardRepository.findById(boardId).orElseThrow(() -> {
			return new IllegalArgumentException("댓글 쓰기 실패 : 게시글 id를 찾을 수 없습니다");
		});

		reply.setMember(member);
		reply.setBoard(board);

		replyRepository.save(reply);

	}

	@Transactional
	public void saveReply(ReplySaveRequestDto replyDto) {

		/*
		 * Member member =
		 * memberRepository.findById(replyDto.getMemberId()).orElseThrow(()->{ return
		 * new IllegalArgumentException("댓글 쓰기 실패 : 회원 id를 찾을 수 없습니다"); });
		 * 
		 * Board board =
		 * boardRepository.findById(replyDto.getBoardId()).orElseThrow(()->{ return new
		 * IllegalArgumentException("댓글 쓰기 실패 : 게시글 id를 찾을 수 없습니다"); });
		 * 
		 * Reply reply = Reply.builder() .content(replyDto.getContent()) .member(member)
		 * .board(board) .build();
		 * 
		 * replyRepository.save(reply);
		 */

		replyRepository.mSave(replyDto.getMemberId(), replyDto.getBoardId(), replyDto.getContent());
	}

	public void deleteReply(int replyId, PrincipalDetail principal) throws Exception {
		Reply reply = replyRepository.findById(replyId).orElseThrow(() -> {
			return new IllegalArgumentException("댓글 삭제 실패 : 댓글 id를 찾을 수 없습니다");
		});

		if (reply.getMember().getId() != principal.getMember().getId()
				&& principal.getMember().getRole() != RoleType.ADMIN) {
			throw new Exception("댓글 삭제 실패 : 삭제 권한이 없습니다.");
		}

		replyRepository.deleteById(replyId);
	}

	public void CKEditorImgUpload(HttpServletRequest req, HttpServletResponse res, MultipartFile upload)
			throws Exception {

		String uploadPath = req.getSession().getServletContext().getRealPath("upload/board");

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
			String ckUploadPath = uploadPath + File.separator + uid + "_" + fileName;

			// System.out.println(uploadPath);
			// System.out.println(ckUploadPath);

			File uploadFile = new File(ckUploadPath);
			uploadFile.createNewFile();
			out = new FileOutputStream(uploadFile);

			out.write(bytes);
			out.flush(); // out에 저장된 데이터를 전송하고 초기화

			// String callback = req.getParameter("CKEditorFuncNum");
			printWriter = res.getWriter();
			String fileUrl = "/upload/board/" + uid + "_" + fileName; // 작성화면
			// 업로드시 메시지 출력
			JSONObject json = new JSONObject();
			json.put("uploaded", 1);
			json.put("fileName", fileName);
			json.put("url", fileUrl);
			printWriter.println(json);

			// System.out.println("test url : " +
			// req.getSession().getServletContext().getRealPath("resouces/ckUpload"));
			// System.out.println("url : " + fileUrl);
			// System.out.println("ckUploadPath : " + ckUploadPath);

			printWriter.flush();
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
