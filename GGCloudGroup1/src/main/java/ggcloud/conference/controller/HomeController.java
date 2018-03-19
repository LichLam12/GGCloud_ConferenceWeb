package ggcloud.conference.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.gson.Gson;

import ggcloud.conference.service.AboutService;
import ggcloud.conference.service.NewsService;

@Controller
public class HomeController {
	
	
	@Autowired
	private AboutService aboutService;
	@Autowired
	private NewsService newService;


	
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
	
	
	@GetMapping("/manage-news")
	public String ManageNews(HttpServletRequest request, HttpServletResponse response) throws IOException {

			System.out.println("Success!");
			request.setAttribute("newss", newService.findAllNews());
			PrintWriter out=response.getWriter(); //Ä‘á»ƒ cho code gá»�n hÆ¡n

			 if (newService.findAllNews() != null) {
		            response.setContentType("application/json");
		            //Import gson-2.2.2.jar
		            Gson gson = new Gson();
		            String objectToReturn = gson.toJson(newService.findAllNews()); //Convert List -> Json
		            out.write(objectToReturn); //Ä�Æ°a Json tráº£ vá»� Ajax
		            out.flush();
					//response.getWriter().write(objectToReturn);
		        } else {
		            response.setContentType("application/json");
		            out.write("{\"check\":\"fail\"}");
		            out.flush();
		        }
	
		
		request.setAttribute("announcement", "Show data successfull");
		request.setAttribute("mode", "LIST");
		return "managingnews";
	}
	
}
