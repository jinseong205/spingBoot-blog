package com.jinseong.blog.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String riotApi(@RequestParam(required=false) String username, Model model) throws Exception {
		
		if (username != null && !username.trim().equals("")) {
			RiotInfo riotInfo = null;
			try {
				riotInfo = riotApiService.riotInfo(username);
			}catch(Exception e){
				riotInfo = riotApiService.riotInfoDummy();
			}
			model.addAttribute("riotInfo", riotInfo);
		}
		return "toy/riot-api/main";
	}

	@GetMapping("/toy/webGame")
	public String webGame(Model model) {
		return "toy/webGame/main"; // viewResolver 작동!
	}

	@GetMapping("toy/smartContract")
	public String smartContract(Model model) {
		return "toy/smartContract/main";
	}

	@GetMapping("toy/webSocketChatting")
	public String webSocketChatting(Model model) {
		return "toy/webSocketChatting/main";
	}
}
