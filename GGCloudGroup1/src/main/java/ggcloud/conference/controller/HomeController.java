package ggcloud.conference.controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import ggcloud.conference.model.News;
import ggcloud.conference.model.About;
import ggcloud.conference.model.Event;

import ggcloud.conference.service.AboutService;
import ggcloud.conference.service.EventService;
import ggcloud.conference.service.NewsService;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.*;
import com.google.api.services.drive.Drive;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

	@Autowired
	private NewsService newService;
	@Autowired
	private AboutService aboutService;

	@Autowired
	private EventService eventService;

	public static String linkTai=null;
	
	@GetMapping("/home")
	public String Home(HttpServletRequest request) {
		request.setAttribute("aboutslist", aboutService.findAllAbout());
		request.setAttribute("eventlist", eventService.findAllEvent());
		request.setAttribute("newslist", newService.findAllNews());

		/*
		 * request.setAttribute("mode", "MODE_TASKS");
		 */ return "index";
	}

	@GetMapping("/news")
	public String News(HttpServletRequest request) {
		request.setAttribute("newslist", newService.findAllNews());
		return "news";
	}


	
	//=============================================================================Managing news
	@GetMapping("/manage-news")
	public String NewsManagementPage(Model model, HttpServletRequest request) {
		model.addAttribute("linkTai",linkTai);
		return "managingnews";
	}

	@GetMapping("/load-newslist")
	public void ShowNewsList(HttpServletRequest request, HttpServletResponse response) throws IOException {

		System.out.println("Success!");
		// request.setAttribute("newss", newService.findAllNews());
		PrintWriter out = response.getWriter(); // Ä‘á»ƒ cho code gá»�n hÆ¡n
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		if (newService.findAllNews() != null) {
			response.setContentType("application/json");
			// Import gson-2.2.2.jar
			Gson gson = new Gson();
			String objectToReturn = gson.toJson(newService.findAllNews()); // Convert List -> Json
			out.write(objectToReturn); // Ä�Æ°a Json tráº£ vá»� Ajax
			out.flush();
			// response.getWriter().write(objectToReturn);
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
	public void ShowOneNews(@RequestParam int id, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		newService.findOneNews(id);

		PrintWriter out = response.getWriter(); // Ä‘á»ƒ cho code gá»�n hÆ¡n
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		if (newService.findOneNews(id) != null) {
			response.setContentType("application/json");
			// Import gson-2.2.2.jar
			Gson gson = new Gson();
			String objectToReturn = gson.toJson((News) newService.findOneNews(id)); // Convert List -> Json
			out.write(objectToReturn); // Ä�Æ°a Json tráº£ vá»� Ajax
			out.flush();
		} else {
			response.setContentType("application/json");
			out.write("{\"check\":\"fail\"}");
			out.flush();
		}

	}

	@GetMapping("/add-news")
	public void AddNews(@RequestParam int id, @RequestParam String title, @RequestParam String openingline,
			@RequestParam String image1, @RequestParam String content1, @RequestParam String image2,
			@RequestParam String content2, @RequestParam String writer, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		News newsvar = new News(id, title, openingline, image1, content1, image2, content2, writer);
		PrintWriter out = response.getWriter(); // Ä‘á»ƒ cho code gá»�n hÆ¡n
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		if (newService.findOneNews(id) == null) {
			newService.UpdateNew(newsvar);

			if (newService.findAllNews() != null) {
				response.setContentType("application/json");
				// Import gson-2.2.2.jar
				Gson gson = new Gson();
				String objectToReturn = gson.toJson(newService.findAllNews()); // Convert List -> Json
				out.write(objectToReturn); // Ä�Æ°a Json tráº£ vá»� Ajax
				out.flush();
				// response.getWriter().write(objectToReturn);
			} else {
				response.setContentType("application/json");
				out.write("{\"check\":\"fail\"}");
				out.flush();
			}
		} else {
			response.setContentType("application/json");
			out.write("{\"check\":\"fail\"}");
			out.flush();
		}
	}

	@GetMapping("/edit-news")
	public void AddEditNews(@RequestParam int id, @RequestParam String title, @RequestParam String openingline,
			@RequestParam String image1, @RequestParam String content1, @RequestParam String image2,
			@RequestParam String content2, @RequestParam String writer, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		News newsvar = new News(id, title, openingline, image1, content1, image2, content2, writer);
		newService.UpdateNew(newsvar);
		PrintWriter out = response.getWriter(); // Ä‘á»ƒ cho code gá»�n hÆ¡n
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		if (newService.findOneNews(id) != null) {

			if (newService.findAllNews() != null) {
				response.setContentType("application/json");
				// Import gson-2.2.2.jar
				Gson gson = new Gson();
				String objectToReturn = gson.toJson(newService.findAllNews()); // Convert List -> Json
				out.write(objectToReturn); // Ä�Æ°a Json tráº£ vá»� Ajax
				out.flush();
				// response.getWriter().write(objectToReturn);
			} else {
				response.setContentType("application/json");
				out.write("{\"check\":\"fail\"}");
				out.flush();
			}
		} else {
			response.setContentType("application/json");
			out.write("{\"check\":\"fail\"}");
			out.flush();
		}
	}

	
	
	//=============================================================================Managing event
		@GetMapping("/manage-event")
		public String EventManagementPage(Model model, HttpServletRequest request) {
			model.addAttribute("linkTai",linkTai);
			return "managingevent";
		}

		@GetMapping("/load-eventlist")
		public void ShowEventList(HttpServletRequest request, HttpServletResponse response) throws IOException {

			System.out.println("Success!");
			// request.setAttribute("newss", newService.findAllNews());
			PrintWriter out = response.getWriter(); // Ä‘á»ƒ cho code gá»�n hÆ¡n
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("utf-8");

			if (eventService.findAllEvent() != null) {
				response.setContentType("application/json");
				// Import gson-2.2.2.jar
				Gson gson = new Gson();
				String objectToReturn = gson.toJson(eventService.findAllEvent()); // Convert List -> Json
				out.write(objectToReturn); // Ä�Æ°a Json tráº£ vá»� Ajax
				out.flush();
				// response.getWriter().write(objectToReturn);
			} else {
				response.setContentType("application/json");
				out.write("{\"check\":\"fail\"}");
				out.flush();
			}
		}

		@GetMapping("/delete-event")
		public void DeleteEvent(@RequestParam int id, HttpServletRequest request) {
			try {
				eventService.Delete(id);
				System.out.println("Delete Successfull");
			} catch (Exception e) {
				System.out.println("Delete Error");
			}
		}

		@GetMapping("/add-event")
		public void AddEvent(@RequestParam int id, @RequestParam String eventname, @RequestParam String eventdate,
				@RequestParam String eventtime, @RequestParam String eventlocation, @RequestParam String lectureravatar,
				@RequestParam String lecturername, @RequestParam String benefit, HttpServletRequest request,
				HttpServletResponse response) throws IOException {

			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date startDate = sdf.parse(eventdate);
				java.sql.Date sqlDate = new java.sql.Date(startDate.getTime());
				System.out.println(sqlDate);
				

				Event eventvar = new Event(id, eventname, startDate, eventtime, eventlocation, lectureravatar, lecturername, benefit);
				PrintWriter out = response.getWriter(); // Ä‘á»ƒ cho code gá»�n hÆ¡n
				response.setContentType("text/html;charset=UTF-8");
				request.setCharacterEncoding("utf-8");

				if (eventService.findEvent(id) == null) {
					eventService.Save(eventvar);

					if (eventService.findAllEvent() != null) {
						response.setContentType("application/json");
						// Import gson-2.2.2.jar
						Gson gson = new Gson();
						String objectToReturn = gson.toJson(eventService.findAllEvent()); // Convert List -> Json
						out.write(objectToReturn); // Ä�Æ°a Json tráº£ vá»� Ajax
						out.flush();
						// response.getWriter().write(objectToReturn);
					} else {
						response.setContentType("application/json");
						out.write("{\"check\":\"fail\"}");
						out.flush();
					}
				} else {
					response.setContentType("application/json");
					out.write("{\"check\":\"fail\"}");
					out.flush();
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			
		}

		@GetMapping("/edit-event")
		public void AddEditEvent(@RequestParam int id, @RequestParam String eventname, @RequestParam String eventdate,
				@RequestParam String eventtime, @RequestParam String eventlocation, @RequestParam String lectureravatar,
				@RequestParam String lecturername, @RequestParam String benefit, HttpServletRequest request,
				HttpServletResponse response) throws IOException {

			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date startDate = sdf.parse(eventdate);
				java.sql.Date sqlDate = new java.sql.Date(startDate.getTime());
				System.out.println(sqlDate);
				
				Event eventvar = new Event(id, eventname, startDate, eventtime, eventlocation, lectureravatar, lecturername, benefit);
				eventService.Save(eventvar);
				PrintWriter out = response.getWriter(); // Ä‘á»ƒ cho code gá»�n hÆ¡n
				response.setContentType("text/html;charset=UTF-8");
				request.setCharacterEncoding("utf-8");

				if (eventService.findEvent(id) != null) {

					if (eventService.findAllEvent() != null) {
						response.setContentType("application/json");
						// Import gson-2.2.2.jar
						Gson gson = new Gson();
						String objectToReturn = gson.toJson(eventService.findAllEvent()); // Convert List -> Json
						out.write(objectToReturn); // Ä�Æ°a Json tráº£ vá»� Ajax
						out.flush();
						// response.getWriter().write(objectToReturn);
					} else {
						response.setContentType("application/json");
						out.write("{\"check\":\"fail\"}");
						out.flush();
					}
				} else {
					response.setContentType("application/json");
					out.write("{\"check\":\"fail\"}");
					out.flush();
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
	
	
	
	//=============================================================================Managing about
	@GetMapping("/manage-about")
	public String AboutManagementPage(Model model, HttpServletRequest request) {
		
		return "managingabout";
	}

	@GetMapping("/load-aboutlist")
	public void ShowAboutList(HttpServletRequest request, HttpServletResponse response) throws IOException {

		System.out.println("Success!");
		// request.setAttribute("newss", newService.findAllNews());
		PrintWriter out = response.getWriter(); // Ä‘á»ƒ cho code gá»�n hÆ¡n
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		if (newService.findAllNews() != null) {
			response.setContentType("application/json");
			// Import gson-2.2.2.jar
			Gson gson = new Gson();
			String objectToReturn = gson.toJson(aboutService.findAllAbout()); // Convert List -> Json
			out.write(objectToReturn); // Ä�Æ°a Json tráº£ vá»� Ajax
			out.flush();
			// response.getWriter().write(objectToReturn);
		} else {
			response.setContentType("application/json");
			out.write("{\"check\":\"fail\"}");
			out.flush();
		}
	}

	@GetMapping("/delete-about")
	public void DeleteAbout(@RequestParam int id, HttpServletRequest request) {
		try {
			aboutService.Delete(id);;
			System.out.println("Delete Successfull");
		} catch (Exception e) {
			System.out.println("Delete Error");
		}
	}

	@GetMapping("/show-one-about")
	public void ShowOneAbout(@RequestParam int id, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		aboutService.findAbout(id);

		PrintWriter out = response.getWriter(); // Ä‘á»ƒ cho code gá»�n hÆ¡n
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		if (aboutService.findAbout(id) != null) {
			response.setContentType("application/json");
			// Import gson-2.2.2.jar
			Gson gson = new Gson();
			String objectToReturn = gson.toJson(aboutService.findAbout(id)); // Convert List -> Json
			out.write(objectToReturn); // Ä�Æ°a Json tráº£ vá»� Ajax
			out.flush();
		} else {
			response.setContentType("application/json");
			out.write("{\"check\":\"fail\"}");
			out.flush();
		}

	}

	@GetMapping("/add-about")
	public void AddAbout(@RequestParam int id, @RequestParam String title, @RequestParam String content,
			@RequestParam String image, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		About aboutvar = new About(id,title,content,image);
		PrintWriter out = response.getWriter(); // Ä‘á»ƒ cho code gá»�n hÆ¡n
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		if (aboutService.findAbout(id) == null) {
			aboutService.Save(aboutvar);

			if (newService.findAllNews() != null) {
				response.setContentType("application/json");
				// Import gson-2.2.2.jar
				Gson gson = new Gson();
				String objectToReturn = gson.toJson(aboutService.findAllAbout()); // Convert List -> Json
				out.write(objectToReturn); // Ä�Æ°a Json tráº£ vá»� Ajax
				out.flush();
				// response.getWriter().write(objectToReturn);
			} else {
				response.setContentType("application/json");
				out.write("{\"check\":\"fail\"}");
				out.flush();
			}
		} else {
			response.setContentType("application/json");
			out.write("{\"check\":\"fail\"}");
			out.flush();
		}
	}

	@GetMapping("/edit-about")
	public void AddEditAbout(@RequestParam int id, @RequestParam String title, @RequestParam String content,
			@RequestParam String image, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		About aboutvar = new About(id,title,content,image);
		aboutService.Save(aboutvar);
		PrintWriter out = response.getWriter(); // Ä‘á»ƒ cho code gá»�n hÆ¡n
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		if (aboutService.findAbout(id) != null) {

			if (aboutService.findAllAbout() != null) {
				response.setContentType("application/json");
				// Import gson-2.2.2.jar
				Gson gson = new Gson();
				String objectToReturn = gson.toJson(aboutService.findAllAbout()); // Convert List -> Json
				out.write(objectToReturn); // Ä�Æ°a Json tráº£ vá»� Ajax
				out.flush();
				// response.getWriter().write(objectToReturn);
			} else {
				response.setContentType("application/json");
				out.write("{\"check\":\"fail\"}");
				out.flush();
			}
		} else {
			response.setContentType("application/json");
			out.write("{\"check\":\"fail\"}");
			out.flush();
		}
	}
	
	
	//=============================================================================Uploadfile to drive
	@PostMapping("/uploadfile")
	public void uploadFile(@RequestParam MultipartFile file, Model model,  HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter(); // Ä‘á»ƒ cho code gá»�n hÆ¡n

		Drive service = getDriveService();
		
		File fileMetadata = new File();
		fileMetadata.setTitle(file.getOriginalFilename());

		java.io.File filePath = new java.io.File("upload-dir/" + file.getOriginalFilename());
		filePath.createNewFile();
		
		FileContent mediaContent = new FileContent(file.getContentType(), filePath);
		File f = service.files().insert(fileMetadata, mediaContent)
		    .setFields("id")
		    .execute();
		
		linkTai = "http://drive.google.com/open?id="+f.getId();

		Path sourceFile = Paths.get("upload-dir/"+file.getOriginalFilename());
		
		response.setContentType("application/json");
		// Import gson-2.2.2.jar
		Gson gson = new Gson();
		String objectToReturn = gson.toJson(linkTai); // Convert List -> Json
		out.write(objectToReturn); // Ä�Æ°a Json tráº£ vá»� Ajax
		out.flush();
		
/*		return "redirect:/manage-news";
*/	}
	
	
	/** Application name. */
    private static final String APPLICATION_NAME =
        "Drive API Java Quickstart";

    /** Directory to store user credentials for this application. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
        System.getProperty("user.home"), ".credentials/drive-java-quickstart");

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =
        JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes required by this quickstart.
     *
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/drive-java-quickstart
     */
    private static final List<String> SCOPES =
        Arrays.asList(DriveScopes.DRIVE);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws Exception 
     */
    public static Credential authorize() throws Exception {
        // Load client secrets.
        InputStream in =
            HomeController.class.getResourceAsStream("/client_secret.json");
        GoogleClientSecrets clientSecrets =
            GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(
            flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

    /**
     * Build and return an authorized Drive client service.
     * @return an authorized Drive client service
     * @throws Exception 
     */
    public static Drive getDriveService() throws Exception {
        Credential credential = authorize();
        return new Drive.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

}
