package ggcloud.conference.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/home")
	public String Home() {
		return "index";
	}
	
	@GetMapping("/news")
	public String News() {
		return "News";
	}
	
	
//	@GetMapping("/event")
//	public String Event() {
//		return "Event";
//	}
	
	
}
