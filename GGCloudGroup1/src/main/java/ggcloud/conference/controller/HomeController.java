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
import ggcloud.conference.service.NewsService;
import ggcloud.conference.model.News;

@Controller
public class HomeController {
	
	
	@Autowired
	private NewsService newService;
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
	
	/*@PostConstruct
	void initialiseSession() {
	    FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	}*/
	@GetMapping("/manage-news")
	public String NewsManagementPage(HttpServletRequest request) {
		/*request.setAttribute("newss", newService.findAllNews());
		request.setAttribute("announcement", "Show data successfull");
		request.setAttribute("mode", "LIST");*/
		return "managingnews";
	}
	
	@GetMapping("/load-newslist")
	public void ShowNewsList(HttpServletRequest request, HttpServletResponse response) throws IOException {

		System.out.println("Success!");
		//request.setAttribute("newss", newService.findAllNews());
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
	
	@SuppressWarnings("unused")
	@GetMapping("/add-news")
	public void AddNews(@RequestParam String content, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		News newsvar= new News(content);
		newService.SaveNew(newsvar);
		
		PrintWriter out=response.getWriter(); //Ä‘á»ƒ cho code gá»�n hÆ¡n

		 if(newsvar != null) {

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
	public void EditNews(@RequestParam int id, @RequestParam String content, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		News newsvar= new News(id,content);
		newService.SaveNew(newsvar);
		PrintWriter out=response.getWriter(); //Ä‘á»ƒ cho code gá»�n hÆ¡n

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
