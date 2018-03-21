package ggcloud.conference.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import ggcloud.conference.service.AboutService;
import ggcloud.conference.service.EventService;
import ggcloud.conference.service.NewsService;
import ggcloud.conference.model.News;

@Controller
public class HomeController {
	
	
	@Autowired
	private NewsService newService;
	@Autowired
	private AboutService aboutService;
	
	@Autowired
	private EventService eventService;

	
	@GetMapping("/home")
	public String Home(HttpServletRequest request) {
		request.setAttribute("aboutslist", aboutService.findAllAbout());
		request.setAttribute("eventlist", eventService.findAllEvent());
/*		request.setAttribute("mode", "MODE_TASKS");
*/		return "index";
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
	public String NewsManagementPage(HttpServletRequest request) {
		
		return "managingnews";
	}
	
	@GetMapping("/load-newslist")
	public void ShowNewsList(HttpServletRequest request, HttpServletResponse response) throws IOException {

		System.out.println("Success!");
		//request.setAttribute("newss", newService.findAllNews());
		PrintWriter out=response.getWriter(); //Ä‘á»ƒ cho code gá»�n hÆ¡n
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
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
	}
	
	@GetMapping("/delete-news")
	public void DeleteNews(@RequestParam int id, HttpServletRequest request) {
		try {
			newService.DeleteNew(id);
			System.out.println("Delete Successfull");
		} catch (Exception e) {
			System.out.println("Delete Error");
		}
	}
	
	@GetMapping("/show-one-news")
	public void ShowOneNews(@RequestParam int id, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		newService.findOneNews(id);
		
		PrintWriter out=response.getWriter(); //Ä‘á»ƒ cho code gá»�n hÆ¡n
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		 if (newService.findOneNews(id) != null) {
	            response.setContentType("application/json");
	            //Import gson-2.2.2.jar
	            Gson gson = new Gson();
	            String objectToReturn = gson.toJson((News)newService.findOneNews(id)); //Convert List -> Json
	            out.write(objectToReturn); //Ä�Æ°a Json tráº£ vá»� Ajax
	            out.flush();
	        } else {
	            response.setContentType("application/json");
	            out.write("{\"check\":\"fail\"}");
	            out.flush();
	        }
		
	}
	
	@GetMapping("/add-news")
	public void AddNews(@RequestParam int id, @RequestParam String title, @RequestParam String openingline, @RequestParam String image1,
			@RequestParam String content1, @RequestParam String image2, @RequestParam String content2, @RequestParam String writer,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		
		News newsvar= new News(id,title,openingline,image1,content1,image2,content2,writer);
		PrintWriter out=response.getWriter(); //Ä‘á»ƒ cho code gá»�n hÆ¡n
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		 if(newService.findOneNews(id) == null) {
				newService.UpdateNew(newsvar);

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
		 }
		 else {
	            response.setContentType("application/json");
	            out.write("{\"check\":\"fail\"}");
	            out.flush();
	        }	
	}
	
	@GetMapping("/edit-news")
	public void AddEditNews(@RequestParam int id, @RequestParam String title, @RequestParam String openingline, @RequestParam String image1,
			@RequestParam String content1, @RequestParam String image2, @RequestParam String content2, @RequestParam String writer,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		
		News newsvar= new News(id,title,openingline,image1,content1,image2,content2,writer);
		newService.UpdateNew(newsvar);
		PrintWriter out=response.getWriter(); //Ä‘á»ƒ cho code gá»�n hÆ¡n
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		 if(newService.findOneNews(id) != null) {

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
		 }
		 else {
	            response.setContentType("application/json");
	            out.write("{\"check\":\"fail\"}");
	            out.flush();
	        }	
	}
	
}
