package com.jinseong.blog.controller.common;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping({"","/"})
	public String index(@PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable, Model model) {
		return "index";	//viewResolver 작동!
	}

}
