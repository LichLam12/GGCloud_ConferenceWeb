package ggcloud.conference.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import ggcloud.conference.model.News;
import ggcloud.conference.service.AboutService;
import ggcloud.conference.service.EventService;
import ggcloud.conference.service.NewsService;

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
		/*
		 * request.setAttribute("mode", "MODE_TASKS");
		 */ return "index";
	}

	@GetMapping("/news")
	public String News(HttpServletRequest request) {
		request.setAttribute("newslist", newService.findAllNews());
		return "news";
	}

	@GetMapping("/management")
	public String Event() {
		return "managingpage";
	}

	@GetMapping("/manage-news")
	public String NewsManagementPage(Model model, HttpServletRequest request) {
		/*
		 * model.addAttribute("files", storageService .loadAll() .map(path ->
		 * MvcUriComponentsBuilder .fromMethodName(HomeController.class, "serveFile",
		 * path.getFileName().toString()) .build().toString())
		 * .collect(Collectors.toList()));
		 */
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

	/** Application name. */
	/*
	 * private static final String APPLICATION_NAME = "Drive API Java Quickstart";
	 * 
	 *//** Directory to store user credentials for this application. */
	/*
	 * private static final java.io.File DATA_STORE_DIR = new java.io.File(
	 * System.getProperty("user.home"), ".credentials/drive-java-quickstart");
	 * 
	 *//** Global instance of the {@link FileDataStoreFactory}. */
	/*
	 * private static FileDataStoreFactory DATA_STORE_FACTORY;
	 * 
	 *//** Global instance of the JSON factory. */
	/*
	 * private static final JsonFactory JSON_FACTORY =
	 * JacksonFactory.getDefaultInstance();
	 * 
	 *//** Global instance of the HTTP transport. */
	/*
	 * private static HttpTransport HTTP_TRANSPORT;
	 * 
	 *//**
		 * Global instance of the scopes required by this quickstart.
		 *
		 * If modifying these scopes, delete your previously saved credentials at
		 * ~/.credentials/drive-java-quickstart
		 *//*
			 * private static final List<String> SCOPES = Arrays.asList(DriveScopes.DRIVE);
			 * //kiểu DRIVE toàn quyền (up file nào up)
			 * 
			 * static { try { HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			 * DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR); } catch
			 * (Throwable t) { t.printStackTrace(); System.exit(1); } }
			 * 
			 * private final StorageService storageService;
			 * 
			 * @Autowired public HomeController(StorageService storageService) {
			 * this.storageService = storageService; }
			 * 
			 * 
			 * 
			 * 
			 * 
			 * @GetMapping("/") public String listUploadedFiles(Model model) throws
			 * IOException {
			 * 
			 * model.addAttribute("files", storageService .loadAll() .map(path ->
			 * MvcUriComponentsBuilder .fromMethodName(HomeController.class, "serveFile",
			 * path.getFileName().toString()) .build().toString())
			 * .collect(Collectors.toList()));
			 * 
			 * return "uploadForm";
			 * 
			 * }
			 * 
			 * @GetMapping("/files/{filename:.+}")
			 * 
			 * @ResponseBody public ResponseEntity<Resource> serveFile(@PathVariable String
			 * filename) {
			 * 
			 * Resource file = storageService.loadAsResource(filename); return
			 * ResponseEntity .ok() .header(HttpHeaders.CONTENT_DISPOSITION,
			 * "attachment; filename=\""+file.getFilename()+"\"") .body(file); }
			 * 
			 * @PostMapping("/") public String handleFileUpload(@RequestParam("file")
			 * MultipartFile file, RedirectAttributes redirectAttributes) throws Exception {
			 * 
			 * storageService.store(file);
			 * 
			 * //google drive Drive service = getDriveService();
			 * 
			 * File fileMetadata = new File(); //kdl file của gg
			 * fileMetadata.setTitle(file.getOriginalFilename()); //file ở đây là dòng 115,
			 * lấy tên chính thống của file đã up //Upload xg tạo file mới dựa vào đường dẫn
			 * này java.io.File filePath = new
			 * java.io.File("upload-dir/"+file.getOriginalFilename()); //Lấy kdl của file
			 * (image/docs...) FileContent mediaContent = new
			 * FileContent(file.getContentType(),filePath); File f =
			 * service.files().insert(fileMetadata, mediaContent) //tìm all dvu insert vào
			 * file mới với id để thực thi .setFields("id") .execute();
			 * //System.out.println("File ID: " + f.getId()+" | "+f.getWebContentLink());
			 * //message trong file html redirectAttributes.addFlashAttribute("message",
			 * "You successfully uploaded " + file.getOriginalFilename() );
			 * redirectAttributes.addFlashAttribute("linktai",
			 * "https://drive.google.com/open?id="+f.getId()); return "redirect:/"; } public
			 * static Credential authorize() throws Exception { // Load client secrets.
			 * InputStream in =
			 * HomeController.class.getResourceAsStream("/client_secret.json");
			 * GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
			 * new InputStreamReader(in));
			 * 
			 * // Build flow and trigger user authorization request.
			 * GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
			 * HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
			 * .setDataStoreFactory(DATA_STORE_FACTORY) .setAccessType("offline") .build();
			 * Credential credential = new AuthorizationCodeInstalledApp( flow, new
			 * LocalServerReceiver()).authorize("user"); System.out.println(
			 * "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath()); return
			 * credential; }
			 * 
			 * 
			 * Build and return an authorized Drive client service.
			 * 
			 * @return an authorized Drive client service
			 * 
			 * @throws Exception
			 * 
			 * public static Drive getDriveService() throws Exception { Credential
			 * credential = authorize(); return new Drive.Builder( HTTP_TRANSPORT,
			 * JSON_FACTORY, credential) .setApplicationName(APPLICATION_NAME) .build(); }
			 * 
			 * @ExceptionHandler(StorageFileNotFoundException.class) public ResponseEntity
			 * handleStorageFileNotFound(StorageFileNotFoundException exc) { return
			 * ResponseEntity.notFound().build(); }
			 */

}
