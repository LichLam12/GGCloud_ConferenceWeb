package ggcloud.conference.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ggcloud.conference.service.AboutService;

@Controller
public class HomeController {
	
	
	@Autowired
	private AboutService aboutService;

	
	@GetMapping("/home")
	public String Home(HttpServletRequest request) {
		request.setAttribute("aboutslist", aboutService.findAllAbout());
		request.setAttribute("mode", "MODE_TASKS");
		return "index";
	}
	
	@GetMapping("/news")
	public String News() {
		return "news";
	}
	
	
	@GetMapping("/management")
	public String Event() {
		return "managingpage";
	}
	
	
}
