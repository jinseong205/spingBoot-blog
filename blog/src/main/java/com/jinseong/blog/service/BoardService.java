package com.jinseong.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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



}
