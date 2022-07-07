package com.jinseong.blog.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ToyController {
	
	@GetMapping("/toy")
	public String toyMain(Model model) {
		return riotApi(model);	//viewResolver 작동!
	}

	@GetMapping("toy/riot-api")
	public String riotApi(Model model) {
		return "toy/riot-api/main";
	}
	
	@GetMapping("/toy/webGame")
	public String webGame(Model model) {
		return "toy/webGame/main";	//viewResolver 작동!
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
