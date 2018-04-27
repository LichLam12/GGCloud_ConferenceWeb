package ggcloud.conference.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import ggcloud.conference.model.News;
import ggcloud.conference.model.About;
import ggcloud.conference.model.Event;

import ggcloud.conference.service.AboutService;
import ggcloud.conference.service.EventService;
import ggcloud.conference.service.NewsService;
import ggcloud.conference.storage.StorageService;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
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
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;


@Controller
public class HomeController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private NewsService newService;
	@Autowired
	private AboutService aboutService;

	@Autowired
	private EventService eventService;

	public static String linkTai = null;

	@GetMapping("/login")
	public String login() {


		return "login";
	}
	
	@GetMapping("/upload")
	public String upload() {


		return "uploadfile";
	}
	
	private static String bucketName     = "samuelbucket1group1";
	private static String endpointUrl     = "https://s3.ap-southeast-1.amazonaws.com";

	@PostMapping("/uploadFile")
	protected String doPost(@RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		
		String fileUrl = "";
		java.io.File file5 = convertMultiPartToFile(file);
        String fileName5 = generateFileName(file);
        fileUrl = endpointUrl + "/" + bucketName + "/" + fileName5;
        
        System.out.println("urrl: "+fileUrl);
		
        request.setAttribute("linkaws", fileUrl);
		AmazonS3 s3client2 = new AmazonS3Client(new ProfileCredentialsProvider());
        try {
            System.out.println("Uploading a new object to S3 from a file\n");
            
            s3client2.putObject(new PutObjectRequest(
	                 bucketName, fileName5, file5).withCannedAcl(CannedAccessControlList.PublicRead));

         } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
            		"means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
            		"means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
            
            return "uploadfile_output";
	}
	
//	@GetMapping("/login")
//	public String LoginChoHieu() {
////		  request.setAttribute("mode", "MODE_TASKS");
//		 return "login";
//	}
//	
	private java.io.File convertMultiPartToFile(MultipartFile file) throws IOException {
	    java.io.File convFile = new java.io.File(file.getOriginalFilename());
	    FileOutputStream fos = new FileOutputStream(convFile);
	    fos.write(file.getBytes());
	    fos.close();
	    return convFile;
	}

	private String generateFileName(MultipartFile multiPart) {
	    return multiPart.getOriginalFilename().replace(" ", "_");
	    

	}
	

	
	
	@GetMapping("/home")
	public String Home(Model model,HttpServletRequest request) {
	
			request.setAttribute("aboutslist", aboutService.findAllAbout());
			request.setAttribute("eventlist", eventService.findAllEvent());
			request.setAttribute("newslist", newService.findAllNews());

			return "index";
	}

	@GetMapping("/news")
	public String News(@RequestParam int id, HttpServletRequest request) {
		String result="home";
		if(newService.findOneNews(id) != null) {
			request.setAttribute("onenews", newService.findOneNews(id));
			result = "news";
		}
		return result;
	}

	// =============================================================================Managing
	// news
	@PostMapping("/manage-news")
	public String NewsManagementPage(Model model, HttpServletRequest request, 
			@RequestParam String username, @RequestParam String password) {
		
		String result="login";

		if(username.equals("admingroup1") && password.equals("12345678"))
		{
			System.out.println("Success2222!");
			
			model.addAttribute("linkTai", linkTai);
			model.addAttribute("username", username);

			result = "managingnews";
		}
		else {
			request.setAttribute("error", "Username or password is not correct");
			result="login";
		}
		return result;		
	}

	@GetMapping("/load-newslist")
	public void ShowNewsList(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		model.addAttribute("files", storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(HomeController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));
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

	@GetMapping("/add-news")
	public void AddNews(@RequestParam int id, @RequestParam String title, @RequestParam String openingline,
			@RequestParam String writer, @RequestParam String publishday,
			@RequestParam String image1, @RequestParam String content1, @RequestParam String image2,
			@RequestParam String content2, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date startDate = sdf.parse(publishday);
			java.sql.Date sqlDate = new java.sql.Date(startDate.getTime());
			System.out.println(sqlDate);
			
			
			News newsvar = new News(id, title, openingline, writer, startDate, content1,image1, content2,image2);
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
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

	@GetMapping("/edit-news")
	public void AddEditNews(@RequestParam int id, @RequestParam String title, @RequestParam String openingline,		
			@RequestParam String writer, @RequestParam String publishday,
			@RequestParam String image1, @RequestParam String content1, @RequestParam String image2,
			@RequestParam String content2, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date startDate = sdf.parse(publishday);
			java.sql.Date sqlDate = new java.sql.Date(startDate.getTime());
			System.out.println(sqlDate);
			
			
			News newsvar = new News(id, title, openingline, writer, startDate, content1,image1, content2,image2);
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
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

	// =============================================================================Managing
	// event
	@GetMapping("/manage-event")
	public String EventManagementPage(Model model, HttpServletRequest request, @RequestParam String username) {
		model.addAttribute("linkTai", linkTai);
		model.addAttribute("username", username);

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

			Event eventvar = new Event(id, eventname, startDate, eventtime, eventlocation, lectureravatar, lecturername,
					benefit);
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

			Event eventvar = new Event(id, eventname, startDate, eventtime, eventlocation, lectureravatar, lecturername,
					benefit);
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

	// =============================================================================Managing
	// about
	@GetMapping("/manage-about")
	public String AboutManagementPage(Model model, HttpServletRequest request, @RequestParam String username) {
		model.addAttribute("username", username);

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
			aboutService.Delete(id);
			;
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
			@RequestParam String image, HttpServletRequest request, HttpServletResponse response) throws IOException {

		About aboutvar = new About(id, title, content, image);
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
			@RequestParam String image, HttpServletRequest request, HttpServletResponse response) throws IOException {

		About aboutvar = new About(id, title, content, image);
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

	// =============================================================================Uploadfile
	// to drive
	
	 private final StorageService storageService;

	    @Autowired
	    public HomeController(StorageService storageService) {
	        this.storageService = storageService;
	    }
	    
	    @GetMapping("/files/{filename:.+}")
	    @ResponseBody
	    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

	        Resource file = storageService.loadAsResource(filename);
	        return ResponseEntity
	                .ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
	                .body(file);
	    }

	    @PostMapping("/uploadfile")
	    public void handleFileUpload(@RequestParam("file") MultipartFile file,
	                                   RedirectAttributes redirectAttributes,HttpServletResponse response) throws Exception {
	    	PrintWriter out = response.getWriter();
	    	storageService.store(file);
	       
	    	//google drive
	        Drive service = getDriveService();
	    	
			File fileMetadata = new File(); //kdl file của gg
			fileMetadata.setTitle(file.getOriginalFilename()); //file ở đây là dòng 115, lấy tên chính thống của file đã up
			//Upload xg tạo file mới dựa vào đường dẫn này
			java.io.File filePath = new java.io.File("upload-dir/"+file.getOriginalFilename());
			//Lấy kdl của file (image/docs...)
			FileContent mediaContent = new FileContent(file.getContentType(),filePath);
			File f = service.files().insert(fileMetadata, mediaContent) //tìm all dvu insert vào file mới với id để thực thi
	        .setFields("id")
	        .execute();
			//System.out.println("File ID: " + f.getId()+" | "+f.getWebContentLink());
			//message trong file html
			 redirectAttributes.addFlashAttribute("message",
		                "You successfully uploaded " + file.getOriginalFilename() );
			 redirectAttributes.addFlashAttribute("linktai","https://drive.google.com/open?id="+f.getId());
			 
			 response.setContentType("application/json");
				// Import gson-2.2.2.jar
				Gson gson = new Gson();
				String objectToReturn = gson.toJson("https://drive.google.com/open?id="+f.getId()); // Convert List -> Json
				out.write(objectToReturn); // Ä�Æ°a Json tráº£ vá»� Ajax
				out.flush();
				
	        /*return "redirect:/";*/
	    }
	/*@PostMapping("/uploadfile")
	public void uploadFile(@RequestParam MultipartFile file, Model model, HttpServletResponse response)
			throws Exception {
		
		PrintWriter out = response.getWriter(); // Ä‘á»ƒ cho code gá»�n hÆ¡n

		Drive service = getDriveService();

		File fileMetadata = new File();
		fileMetadata.setTitle(file.getOriginalFilename());

		java.io.File filePath = new java.io.File("upload-dir/" + file.getOriginalFilename());
		filePath.createNewFile();

		FileContent mediaContent = new FileContent(file.getContentType(), filePath);
		File f = service.files().insert(fileMetadata, mediaContent).setFields("id").execute();
		
		Permission newPermission = new Permission();
		newPermission.setValue("");
		newPermission.setType("anyone");
		newPermission.setRole("reader");
		service.permissions().insert(f.getId(), newPermission).execute();
		
		linkTai = "https://drive.google.com/open?id=" + f.getId();

		Path sourceFile = Paths.get("upload-dir/" + file.getOriginalFilename());

		response.setContentType("application/json");
		// Import gson-2.2.2.jar
		Gson gson = new Gson();
		String objectToReturn = gson.toJson(linkTai); // Convert List -> Json
		out.write(objectToReturn); // Ä�Æ°a Json tráº£ vá»� Ajax
		out.flush();

		
		 * return "redirect:/manage-news";
		  }
*/
	/** Application name. */
	private static final String APPLICATION_NAME = "Drive API Java Quickstart";

	/** Directory to store user credentials for this application. */
	private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"),
			".credentials2/drive-java-quickstart");

	/** Global instance of the {@link FileDataStoreFactory}. */
	private static FileDataStoreFactory DATA_STORE_FACTORY;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	/** Global instance of the HTTP transport. */
	private static HttpTransport HTTP_TRANSPORT;

	/**
	 * Global instance of the scopes required by this quickstart.
	 *
	 * If modifying these scopes, delete your previously saved credentials at
	 * ~/.credentials/drive-java-quickstart
	 */
	private static final List<String> SCOPES = Arrays.asList(DriveScopes.DRIVE);

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
	 * 
	 * @return an authorized Credential object.
	 * @throws Exception
	 */
	public static Credential authorize() throws Exception {
		// Load client secrets.
		InputStream in = HomeController.class.getResourceAsStream("/client_secret.json");
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES).setDataStoreFactory(DATA_STORE_FACTORY).setAccessType("offline").build();
		Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
		System.out.println("Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
		return credential;
	}

	/**
	 * Build and return an authorized Drive client service.
	 * 
	 * @return an authorized Drive client service
	 * @throws Exception
	 */
	public static Drive getDriveService() throws Exception {
		Credential credential = authorize();
		return new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
	}

}
