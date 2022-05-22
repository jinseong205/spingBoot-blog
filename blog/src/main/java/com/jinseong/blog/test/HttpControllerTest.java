package com.jinseong.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 -> 응답 (Html 파일)
//Controller

//사용자:Req -> Res(Data) 
@RestController
public class HttpControllerTest {
	@GetMapping("http/get")
	public String getTest(Member m) { 
		//?id=1&username=jinseong&password=123&email=jinseong205@gmail.com - Mapping>> MessageConveter(SpringBoot)
		//@requestParam
		return "get 요청 : " + m.getId() + ", " + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
	}
	@PostMapping("http/post") //text/plain, application/json
	public String postTest(@RequestBody Member m) { 
		//json - Mapping >> MessageConveter (SpringBoot)
		//@requestBody
		return "post 요청 : " + m.getId() + ", " + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
	}
	@PutMapping("http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 : " + m.getId() + ", " + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
	}
	@DeleteMapping("http/delete")
	public String deleteTest(@RequestBody Member m) {
		return "delete 요청 : " + m.getId() + ", " + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
	}

}
