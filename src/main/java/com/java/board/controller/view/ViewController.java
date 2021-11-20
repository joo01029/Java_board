package com.java.board.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
	@RequestMapping
	public String mainPage(){
		return "main";
	}

	@RequestMapping("detail")
	public String detailPage(){
		return "detail";
	}
}
