package com.jinseong.blog.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jinseong.blog.auth.PrincipalDetail;
import com.jinseong.blog.model.RiotInfo;
import com.jinseong.blog.service.RiotApiService;

@Controller
public class ToyController {

	@Autowired
	private RiotApiService riotApiService;

	@GetMapping("/toy")
	public String toyMain(Model model) throws Exception {
		return riotApi(null, model); // viewResolver 작동!
	}

	@GetMapping("toy/riot-api")
	public String riotApi(@RequestParam(required = false) String username, Model model) throws Exception {

		if (username != null && !username.trim().equals("")) {
			RiotInfo riotInfo = null;
			try {
				riotInfo = riotApiService.riotInfo(username);
			} catch (Exception e) {
				e.printStackTrace();
				riotInfo = riotApiService.riotInfoDummy();
			} finally {
				model.addAttribute("riotInfo", riotInfo);
			}
		}

		return "toy/riot-api/main";
	}

	@GetMapping("/toy/webGame")
	public String webGame(Model model) {
		return "toy/webGame/main"; 
	}
	

	@GetMapping("toy/webSocketChatting")
	public String webSocketChatting(@AuthenticationPrincipal PrincipalDetail principal, Model model) {
		
		
		return "toy/webSocketChatting/main";
	}
	
	/*
	@GetMapping("toy/smartContract")
	public String smartContract(Model model) {
		return "toy/smartContract/main";
	}
	*/
}
