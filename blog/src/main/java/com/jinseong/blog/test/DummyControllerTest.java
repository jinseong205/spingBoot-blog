package com.jinseong.blog.test;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jinseong.blog.model.Member;
import com.jinseong.blog.model.RoleType;
import com.jinseong.blog.repository.MemberRepository;

//html 파일이 아니라 data를 return
@RestController
public class DummyControllerTest {

	@Autowired // 의존성 주입(DI) >> 메모리 할당
	private MemberRepository memberRpository;

	// http://localhost:8000/dummy/member/{id}
	@DeleteMapping("/dummy/member/{id}")
	public String deleteMember(@PathVariable int id) {
		try{
			memberRpository.deleteById(id);
		}catch(EmptyResultDataAccessException e){
			return "삭제에 실패하였습니다. 해당 id는 존재하지 않습니다";
		}
		return "삭제 되었습니다 id : " + id;
	}
	
	// save > id 정보 없으면 insert, id 정보 있으면 update
	// email,password
	// http://localhost:8000/dummy/member/{id}
	@Transactional
	@PutMapping("/dummy/member/{id}")
	public Member updateMember(@PathVariable int id, @RequestBody Member reqMember) {//json Data -> Java Obejct
		System.out.println(id);
		System.out.println(reqMember.getPassword());
		System.out.println(reqMember.getEmail());
		
		Member member = memberRpository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다");
		});
		
		member.setPassword(reqMember.getPassword());
		member.setEmail(reqMember.getEmail());
	
		//memberRpository.save(reqMember);
		
		//dirty Checking
		return member;
	}
	
	// http://localhost:8000/dummy/memberAll
	@GetMapping("/dummy/memberAll")
	public List<Member> list(){
		return memberRpository.findAll();
	}
	
	// http://localhost:8000/dummy/members?page=0
	// http://localhost:8000/dummy/members?page=1
	@GetMapping("/dummy/members")
	public List<Member> pageList(@PageableDefault(size = 2, sort = "id", direction = Direction.DESC) Pageable pageable){
		Page<Member> pagingMembers = memberRpository.findAll(pageable);
		//content
		List<Member> members = pagingMembers.getContent();
		return members;
	}
	
	// http://localhost:8000/dummy/member/3
	// {id} 주소로 param을 전달 받을 수 있음
	@GetMapping("/dummy/member/{id}")
	public Member Detail(@PathVariable int id) {
		/*
		 * http://localhost:8000/dummy/member/4 을 찾으면 db에서 못찾아오게 되면 member는 null
		 * Optional로 member 객체를 가져와서 null 판단 .orElseThrow() 으로 exception 발생
		 */


		Member member = memberRpository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 유저는 없습니다. id :" + id);
		});

		
		/*		
		 * Member member = memberRpository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id :" + id);
			}
		});	
		*/
		
		//member object = java object > json(Gson라이브러리)
		//spring boot = MessageConveter가 자동으로 json으로 변환해해서 browser에게 전달
		return member;
	}

	// http://localhost:8000/dummy/join(요청)
	// http 의 body에 username, password, email 데이터를 가지고 요청
	@PostMapping("/dummy/join")
	public String join(Member m) {

		System.out.println("id : " + m.getId());
		System.out.println("username : " + m.getUsername());
		System.out.println("password : " + m.getPassword());
		System.out.println("email : " + m.getEmail());
		System.out.println("role : " + m.getRole());
		System.out.println("createDate : " + m.getCreateDate());

		m.setRole(RoleType.USER);
		memberRpository.save(m);

		return "complete";
	}
}
